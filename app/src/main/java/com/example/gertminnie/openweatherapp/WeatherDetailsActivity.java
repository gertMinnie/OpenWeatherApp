package com.example.gertminnie.openweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gertminnie.openweatherapp.GsonFiles.WeatherForecast;
import com.example.gertminnie.openweatherapp.Services.IWeatherService;
import com.example.gertminnie.openweatherapp.Services.WeatherService;
import com.squareup.picasso.Picasso;

public class WeatherDetailsActivity extends AppCompatActivity {

    IWeatherService iWeatherService = new WeatherService();
    WeatherForecast weatherForecast = new WeatherForecast();
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        // GET INTENT FROM CLASS
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");

        // DISPLAY PLACE NAME ATTAINED FROM INTENT
        TextView textView = findViewById(R.id.place);
        textView.setText(getIntent().getStringExtra("city"));

        //Seperation
        iWeatherService.getWeather(city);

        boolean weatherReceived = iWeatherService.weatherReceived();
        while(weatherReceived == false){
            weatherReceived = iWeatherService.weatherReceived();
        }

        //DISPLAY PLACE
        TextView place = findViewById(R.id.place);
        place.setText(iWeatherService.getPlace());

        //DISPLAY IMAGE
        icon = findViewById(R.id.weatherIcon);
        loadImageFromUrl(iWeatherService.getIcon());

        //DISPLAY TEMPERATURE
        TextView temperature = findViewById(R.id.temperature);
        temperature.setText(iWeatherService.getTemperature());

        //DISPLAY SKIES
        TextView skies = findViewById(R.id.skies);
        skies.setText(iWeatherService.getSkies());

        //DISPLAY TIME
        TextView time = findViewById(R.id.time);
        time.setText(iWeatherService.getTime());

        //DISPLAY HUMIDITY
        TextView wind = findViewById(R.id.wind);
        wind.setText(iWeatherService.getWind());

        //DISPLAY CLOUDINESS
        TextView cloudiness = findViewById(R.id.cloudinessOutput);
        cloudiness.setText(iWeatherService.getCloudiness());

        //DISPLAY HUMIDITY
        TextView pressure = findViewById(R.id.pressure);
        pressure.setText(iWeatherService.getPressure());

        //DISPLAY HUMIDITY
        TextView humidity = findViewById(R.id.humidity);
        humidity.setText(iWeatherService.getHumidity());

        //DISPLAY SUNRISE
        TextView sunrise = findViewById(R.id.sunrise);
        sunrise.setText(iWeatherService.getSunrise());

        //DISPLAY SUNSET
        TextView sunset = findViewById(R.id.sunset);
        sunset.setText(iWeatherService.getSunset());

        //DISPLAY COORDINATES
        TextView coords = findViewById(R.id.coords);
        coords.setText(iWeatherService.getCoords());



    }
    private void loadImageFromUrl(String url) {
        Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).into(icon);
    }
}
