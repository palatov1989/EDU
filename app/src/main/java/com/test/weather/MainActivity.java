package com.test.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Date;


public class MainActivity extends AppCompatActivity
                            implements SeekBar.OnSeekBarChangeListener
                                        //,View.OnClickListener
{   private ViewBinder binder;
    private Task_attr attr;
    private String BASE_URL;
    private void doSeekBar(SeekBar seekBar){
        long bar = 5*24*60*60/100*seekBar.getProgress();
        Date date = new Date();
        bar = bar + date.getTime();
        attr.setDate(new Date(bar));
        binder.bind(attr);
    }                                                       //magic number of 5 days in utc.unix-time
                                                            //divided by 100 percent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        BASE_URL = getString(R.string.base_url);
        BASE_URL += "Sevastopol,ua&" + getString(R.string.api_url);

        binder = new ViewBinder();
        attr = new Task_attr(new Date(), findViewById(R.id.main_activity), binder);
        new LoadParseTask(attr).execute(BASE_URL);

        View v = findViewById(R.id.city);
        final SeekBar s = (SeekBar) findViewById(R.id.seekBar);
        s.setOnSeekBarChangeListener(this);
        //      v.setOnClickListener(this);
    }/*
        @Override
        public void onClick(View view) {
            EditText input = (EditText) view;
            String city = input.getText().toString();
            BASE_URL = getString(R.string.base_url);
            BASE_URL += city+"&";
            BASE_URL += getString(R.string.api_url);
            new LoadParseTask(attr).execute(BASE_URL);
        }*/
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {//doSeekBar(seekBar);
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { doSeekBar(seekBar);
        }
}
