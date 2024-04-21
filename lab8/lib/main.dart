import 'dart:convert';
import 'dart:typed_data';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:carousel_slider/carousel_slider.dart';
import 'package:connectivity/connectivity.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Car API Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: CarPage(),
    );
  }
}

class CarPage extends StatefulWidget {
  @override
  _CarPageState createState() => _CarPageState();
}

class _CarPageState extends State<CarPage> {
  List<String> carImages = [];
  int currentIndex = 0;
  bool isConnected = true;

  @override
  void initState() {
    super.initState();
    fetchCachedImages();
    checkInternetConnection();
  }

  Future<void> checkInternetConnection() async {
    var connectivityResult = await Connectivity().checkConnectivity();
    if (connectivityResult == ConnectivityResult.none) {
      setState(() {
        isConnected = false;
      });
      print('internet -');
    } else {
      print('internet +');
      fetchCarImages();
    }
  }

  Future<void> fetchCarImages() async {
    try {
      final response = await http.get(Uri.parse(
          'https://api.unsplash.com/photos?query=cars&count=10&client_id=UROIzpoCJ6z_08xUTvz_dqcCuTVNXVAJsNeoYNwZYgk'));
      if (response.statusCode == 200) {
        final List<dynamic> data = json.decode(response.body);
        SharedPreferences prefs = await SharedPreferences.getInstance();
        List<String> imageUrls = [];
        for (var item in data) {
          String imageUrl = item['urls']['regular'];
          imageUrls.add(imageUrl);
          if (isConnected) {
            await precacheImage(NetworkImage(imageUrl), context);
            await downloadAndCacheImage(imageUrl, prefs);
          }
        }
        setState(() {
          carImages = imageUrls;
        });
      } else {
        throw Exception('Failed to load car images');
      }
    } catch (e) {
      print('Error fetching images: $e');
    }
  }

  Future<void> fetchCachedImages() async {
    try {
      SharedPreferences prefs = await SharedPreferences.getInstance();
      final List<String>? cachedImages = prefs.getStringList('cached_images');
      print(cachedImages);
      if (cachedImages != null && cachedImages.isNotEmpty) {
        setState(() {
          carImages = cachedImages;
        });
      } else {
        setState(() {
          carImages = [];
        });
      }
    } catch (e) {
      print('Error fetching cached images: $e');
    }
  }

  Widget buildImageWidget(String imageUrl) {
    return isConnected
        ? CachedNetworkImage(
      imageUrl: imageUrl,
      fit: BoxFit.cover,
      placeholder: (context, url) => CircularProgressIndicator(),
      errorWidget: (context, url, error) => Icon(Icons.error),
    )
        : FutureBuilder<Uint8List?>(
      future: getImageFromCache(imageUrl),
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          return Image.memory(
            snapshot.data!,
            fit: BoxFit.cover,
          );
        } else {
          return Icon(Icons.error);
        }
      },
    );
  }

  Future<Uint8List?> getImageFromCache(String imageUrl) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    final String? base64String = prefs.getString(imageUrl);
    if (base64String != null) {
      return base64Decode(base64String);
    } else {
      return null;
    }
  }

  Future<void> downloadAndCacheImage(
      String imageUrl, SharedPreferences prefs) async {
    try {
      final response = await http.get(Uri.parse(imageUrl));
      if (response.statusCode == 200) {
        await prefs.setString(imageUrl, base64Encode(response.bodyBytes));
        List<String>? cachedImages = prefs.getStringList('cached_images');
        if (cachedImages == null) {
          cachedImages = [];
        }
        cachedImages.add(imageUrl);
        await prefs.setStringList('cached_images', cachedImages);
      } else {
        throw Exception('Failed to load image from API');
      }
    } catch (e) {
      print('Error downloading image from API: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Car Images'),
      ),
      body: carImages.isEmpty
          ? Center(
        child: CircularProgressIndicator(),
      )
          : Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          CarouselSlider(
            options: CarouselOptions(
              height: 400,
              enlargeCenterPage: true,
              onPageChanged: (index, _) {
                setState(() {
                  currentIndex = index;
                });
              },
            ),
            items: carImages.map((imageUrl) {
              return Builder(
                builder: (BuildContext context) {
                  return Container(
                    width: MediaQuery.of(context).size.width,
                    margin: EdgeInsets.symmetric(horizontal: 5.0),
                    decoration: BoxDecoration(
                      color: Colors.white,
                    ),
                    child: buildImageWidget(imageUrl),
                  );
                },
              );
            }).toList(),
          ),
        ],
      ),
    );
  }
}
