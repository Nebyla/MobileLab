import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:carousel_slider/carousel_slider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Cat API Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: CatPage(),
    );
  }
}

class CatPage extends StatefulWidget {
  @override
  _CatPageState createState() => _CatPageState();
}

class _CatPageState extends State<CatPage> {
  List<String> catImages = [];
  int currentIndex = 0;

  @override
  void initState() {
    super.initState();
    fetchCatImages();
  }

  Future<void> fetchCatImages() async {
    final response = await http.get(Uri.parse('https://api.thecatapi.com/v1/images/search?limit=10'));

    if (response.statusCode == 200) {
      final List<dynamic> data = json.decode(response.body);
      setState(() {
        catImages = data.map<String>((imageData) => imageData['url']).toList();
      });
    } else {
      throw Exception('Failed to load cat images');
    }
  }

  void previousImage() {
    setState(() {
      currentIndex = (currentIndex - 1) % catImages.length;
    });
  }

  void nextImage() {
    setState(() {
      currentIndex = (currentIndex + 1) % catImages.length;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Cat Images'),
      ),
      body: catImages.isEmpty
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
            items: catImages.map((imageUrl) {
              return Builder(
                builder: (BuildContext context) {
                  return Container(
                    width: MediaQuery.of(context).size.width,
                    margin: EdgeInsets.symmetric(horizontal: 5.0),
                    decoration: BoxDecoration(
                      color: Colors.white,
                    ),
                    child: Image.network(
                      imageUrl,
                      fit: BoxFit.cover,
                    ),
                  );
                },
              );
            }).toList(),
          ),
          // Row(
          //   mainAxisAlignment: MainAxisAlignment.center,
          //   children: [
          //     ElevatedButton(
          //       onPressed: currentIndex == 0 ? null : previousImage,
          //       child: Text('Previous'),
          //     ),
          //     SizedBox(width: 20),
          //     ElevatedButton(
          //       onPressed: currentIndex == catImages.length - 1 ? null : nextImage,
          //       child: Text('Next'),
          //     ),
          //   ],
          // ),
        ],
      ),
    );
  }
}
