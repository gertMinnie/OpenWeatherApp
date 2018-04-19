package com.example.gertminnie.openweatherapp.Services;

import com.example.gertminnie.openweatherapp.GsonFiles.WeatherForecast;

public interface IWeatherService {

    void getWeather(String inputCity);

    boolean weatherReceived();

    String getPlace();

    String getIcon();

    String getTemperature();

    String getSkies();

    String getTime();

    String getWind();

    String getCloudiness();

    String getPressure();

    String getHumidity();

    String getSunrise();

    String getSunset();

    String getCoords();


}
