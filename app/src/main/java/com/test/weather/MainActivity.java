package com.test.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity
                            implements SeekBar.OnSeekBarChangeListener,
                                            TextView.OnEditorActionListener {
    private Task_attr attr;
    private String BASE_URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        SeekBar s = (SeekBar) findViewById(R.id.seekBar);
        s.setOnSeekBarChangeListener(this);
        BASE_URL = getString(R.string.base_url);
        BASE_URL += "Sevastopol&" + getString(R.string.api_url);

        attr = new Task_attr(this, new Date(), new ViewBinder(new ArrayList<week_cast_element>()));
        new LoadParseTask(attr).execute(BASE_URL);

        EditText t = (EditText) findViewById(R.id.city);
        t.setOnEditorActionListener(this);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerAdapter mAdapter = new RecyclerAdapter(attr.getBinder().getForecast_arr());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onEditorAction(TextView e, int actionId, KeyEvent keyEvent) {
        if ((e.getId()==R.id.city)&&(actionId == EditorInfo.IME_ACTION_DONE)) {
            if (attr.getBinder().getCity().getName() != e.toString()) {
                String str = e.getText().toString();
                if (str != "") {
                    str += "&";
                } else {
                    str = "Sevastopol,ua&";
                }
                BASE_URL = getString(R.string.base_url);
                BASE_URL += str + getString(R.string.api_url);
                new LoadParseTask(attr).execute(BASE_URL);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        long Bar = 5 * 24 * 60 * 60 * 1000 / 100 * seekBar.getProgress();
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
