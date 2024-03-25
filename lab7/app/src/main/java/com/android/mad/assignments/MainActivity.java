package com.android.mad.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View textView = findViewById(R.id.first_activity_text_view);
        textView.setOnClickListener(moveToNextScreen());
    }
    private View.OnClickListener moveToNextScreen(){
        return view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            String transmittedString = "string to transmit";
            intent.putExtra(SecondActivity.TRANSMITTED_STRING, transmittedString);
            Integer transmittedInt = 12;
            intent.putExtra(SecondActivity.TRANSMITTED_INT, transmittedInt);
            Boolean transmittedBoolean = false;
            intent.putExtra(SecondActivity.TRANSMITTED_BOOLEAN, transmittedBoolean);
            startActivity(intent);
        };
    }
}