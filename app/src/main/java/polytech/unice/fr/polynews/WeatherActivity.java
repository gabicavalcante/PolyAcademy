package polytech.unice.fr.polynews;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import polytech.unice.fr.polynews.database.Channel;
import polytech.unice.fr.polynews.database.Item;
import polytech.unice.fr.polynews.service.WeatherServiceCallBak;
import polytech.unice.fr.polynews.service.YahooWeatherService;


public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallBak {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    public void OnCreate(@Nullable Bundle OnSavedView) {
        super.onCreate(OnSavedView);
        setContentView(R.layout.layout_weather);

        weatherIconImageView = (ImageView) findViewById(R.id.imageWeather);
        temperatureTextView = (TextView) findViewById(R.id.temperature);
        conditionTextView = (TextView) findViewById(R.id.condition);
        locationTextView = (TextView) findViewById(R.id.location);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Austin, TX");
    }

    @Override
    public void serviceSucces(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();

        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawble = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawble);
        System.out.println(item.getCondition().getTemperature());
        System.out.println(item.getCondition().getDescription());

        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnit().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());
    }

    @Override
    public void serviceFaillure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
