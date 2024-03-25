package com.android.mad.assignments;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String TRANSMITTED_STRING = "transmittedString";

    public static final String TRANSMITTED_INT = "transmittedInt";

    public static final String TRANSMITTED_BOOLEAN = "transmittedBoolean";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        String transmittedString = getIntent().getStringExtra(TRANSMITTED_STRING) ;
        int transmittedInt = getIntent().getIntExtra(TRANSMITTED_INT,-1);
        boolean transmittedBoolean = getIntent() .getBooleanExtra(TRANSMITTED_BOOLEAN, false);


        TextView textView = findViewById(R.id.second_activity_text_view);
        textView.setText("These values were passed from previous screen"
                + ": transmittedString: " + transmittedString
                +  "transmittedInt: " + transmittedInt
                + "transmittedBoolean: " + transmittedBoolean);
    }

}