package com.example.gertminnie.openweatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("Last entered city", MODE_PRIVATE);
        String city = prefs.getString("city", null);
        if (city != null) {
            EditText cityName = findViewById(R.id.editText);
            cityName.setText(city);
        }
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, WeatherDetailsActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String city = editText.getText().toString();

        SharedPreferences.Editor editor = getSharedPreferences("Last entered city", MODE_PRIVATE).edit();
        editor.putString("city", city);
        editor.apply();

        intent.putExtra("city", city);
        startActivity(intent);
    }


}
