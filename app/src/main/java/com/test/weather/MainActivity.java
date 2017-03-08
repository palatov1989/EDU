package com.test.weather;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  {
    int logo_id[] = new int[8];

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

        for (int i = 0; i < 7; i++) {//creating array of main activity resource IDs
            logo_id[i] = getResources().getIdentifier("w" + i, "drawable", "test.weather");
        }
        new LoadParseTask(findViewById(R.id.main_activity))
                .execute("http://api.openweathermap.org/data/2.5/forecast?q=Sevastopol,ru&appid=0b1f143cf935fb7631300690e14492a7&units=metric");

        CircleImageView wind_direction = new CircleImageView(this);

    }


}
