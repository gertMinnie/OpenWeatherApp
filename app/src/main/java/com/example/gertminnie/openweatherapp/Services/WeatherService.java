package com.example.gertminnie.openweatherapp.Services;

import android.text.format.DateFormat;
import android.util.Log;

import com.example.gertminnie.openweatherapp.GsonFiles.WeatherForecast;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Locale;

public class WeatherService implements IWeatherService{
    WeatherForecast weatherForecast;

    @Override
    public void getWeather(String city) {
        boolean responseReceived = false;

        String requestString = "http://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&units=metric&APPID=dbdfe5c7ef80b4f61b7f0349930c8c8c";

        Fuel.get(requestString).responseString(new Handler<String>() {

            public void failure(Request request, Response response, FuelError error) {
                Log.d("DATA:","EPIC FAIL");
                Log.d("ERROR",error +"");
            }

            public void success(Request request, Response response, String data) {
                Log.d("DATA:",data);
                Gson gson = new Gson();
                weatherForecast = gson.fromJson(data, WeatherForecast.class);
                Log.d("name:", weatherForecast.getName());
                Log.d("temp",weatherForecast.getMain().getTemp()+"c");
            }
        });

//        return weatherForecast;
    }

    public boolean weatherReceived(){
        if (weatherForecast == null){
            return false;
        } else {
            return true;
        }
    }

    public String getPlace(){
        return weatherForecast.getName() +", " +weatherForecast.getSys().getCountry();
    }

    public String getIcon(){
        return "http://openweathermap.org/img/w/" + weatherForecast.getWeather().get(0).getIcon() + ".png";
    }

    public String getTemperature(){
        return weatherForecast.getMain().getTemp() + " c";
    }

    public String getSkies(){
        return weatherForecast.getWeather().get(0).getMain();
    }

    public String getTime(){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);

        calendar.setTimeInMillis(weatherForecast.getDt() * 1000L);
        String date = DateFormat.format("yyyy/MM/dd HH:mm", calendar).toString();

        return "get at "  + date;
    }

    public String getWind(){
        String wind = "";
        int windSpeed = (int) Math.round(weatherForecast.getWind().getSpeed());

        return wind;
    }

    public String getCloudiness(){
        String cloudiness = "";
        boolean day = false;
        Calendar now = Calendar.getInstance(Locale.ENGLISH);

        if (now.getTimeInMillis() > (weatherForecast.getSys().getSunrise() * 1000L) && now.getTimeInMillis() < (weatherForecast.getSys().getSunset() * 1000L)) {
            day = true;
        }

        int percentage = weatherForecast.getClouds().getAll();
        if (percentage <= 10) {
            if (day) {
                cloudiness = "Sunny";
            } else {
                cloudiness = "Clear";
            }
        } else if (percentage <= 20) {
            if (day) {
                cloudiness = "Sunny to mostly sunny";
            } else {
                cloudiness = "Fair";
            }
        } else if (percentage <= 30) {
            if (day) {
                cloudiness = "Mostly sunny";
            } else {
                cloudiness = "Mostly fair";
            }
        } else if (percentage <= 60) {
            if (day) {
                cloudiness = "Partly sunny";
            } else {
                cloudiness = "Partly cloudy";
            }
        } else if (percentage <= 90) {
            cloudiness = "Mostly cloudy";
        } else {
            cloudiness = "Cloudy";
        }

        return cloudiness;
    }

    public String getPressure(){
        return weatherForecast.getMain().getPressure() + " hpa";
    }

    public String getHumidity(){
        return weatherForecast.getMain().getHumidity() + "%";
    }

    public String getSunrise(){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);

        calendar.setTimeInMillis(weatherForecast.getSys().getSunrise() * 1000L);
        String sunrise = DateFormat.format("HH:mm", calendar).toString();

        return sunrise;
    }

    public String getSunset(){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);

        calendar.setTimeInMillis(weatherForecast.getSys().getSunset() * 1000L);
        String sunset = DateFormat.format("HH:mm", calendar).toString();

        return sunset;
    }

    public String getCoords(){
        return "[" + weatherForecast.getCoord().getLat() + ", " + weatherForecast.getCoord().getLon() + "]";
    }





}
