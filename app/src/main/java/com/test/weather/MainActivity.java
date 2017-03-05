package com.test.weather;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  {
    int logo_id[] = new int[8];
    View activity;
    void getWeatherImg(String w) {
        String url = new String(getResources().getString(R.string.weather_img_url));
        new DownloadImageTask((ImageView) findViewById(R.id.weather_img)).execute(url + w + ".png");
    }
    void setWeatherLogo(ImageView img, String w) {
        char i = w.charAt(0);
        img.setImageResource(logo_id[i]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ImageView weather_logo = (ImageView) findViewById(R.id.weather_logo);

        for (int i = 0; i < 7; i++) {//creating array of R.drawable.ID
            logo_id[i] = getResources().getIdentifier("w" + i, "drawable", "test.weather");
        }

        activity.findViewById(R.id.main_activity);
        new LoadParseTask(activity).execute("http://api.openweathermap.org/data/2.5/forecast?q=London,us");


        CircleImageView wind_direction = new CircleImageView(this);

    }


}
