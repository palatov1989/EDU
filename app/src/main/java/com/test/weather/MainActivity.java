package com.test.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;
import android.widget.SeekBar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
                            implements SeekBar.OnSeekBarChangeListener

{
    private Task_attr   attr;
    private String      BASE_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        SeekBar s = (SeekBar) findViewById(R.id.seekBar);
        s.setOnSeekBarChangeListener(this);
        BASE_URL = getString(R.string.base_url);
        BASE_URL += "Sevastopol&" + getString(R.string.api_url);

        attr = new Task_attr(this, new Date(), new ViewBinder());
        new LoadParseTask(attr).execute(BASE_URL);

        EditText t = (EditText) findViewById(R.id.city);
        t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable e) {
                if (attr.getBinder().getCity().getName() != e.toString()) {
                    String str = e.toString();
                    if (str != "") {
                        str += "&";
                    } else {
                        str = "sevastopol,ua&";
                    }
                    EditText t = (EditText) findViewById(R.id.city);
                    t.removeTextChangedListener(this);

                    BASE_URL = getString(R.string.base_url);
                    BASE_URL += str + getString(R.string.api_url);
                    new LoadParseTask(attr).execute(BASE_URL);

                    t.addTextChangedListener(this);
                }
            }
        });
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        long Bar = 5 * 24 * 60 * 60 * 1000 * seekBar.getProgress() / 100;
        attr.getDate().setTime(Bar + attr.getDate().getTime());
        attr.getBinder().bind(attr);
        }
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            //doSeekBar(seekBar);
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
}