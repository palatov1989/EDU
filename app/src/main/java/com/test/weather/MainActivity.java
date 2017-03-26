package com.test.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Date;


public class MainActivity extends AppCompatActivity
                            implements SeekBar.OnSeekBarChangeListener,
                                            TextView.OnEditorActionListener {
    private String URL;
    private RecyclerAdapter mAdapter;
    private City city;
    private ViewBinder binder;
    private AppCompatActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity=this;
        SeekBar s = (SeekBar) findViewById(R.id.seekBar);
        s.setOnSeekBarChangeListener(this);
        URL = getString(R.string.base_url);
        URL += "Sevastopol&" + getString(R.string.api_url);

        LoadParseTask asyncTask = new LoadParseTask(){
            @Override
            protected void onPostExecute(City city) {
                super.onPostExecute(city);

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(mainActivity);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new RecyclerAdapter();
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setList(binder.getForecastArray());
            }
        };
        binder = new ViewBinder(this);
        asyncTask.setBinder(binder);
        asyncTask.execute(URL);

        EditText t = (EditText) findViewById(R.id.city);
        t.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView e, int actionId, KeyEvent keyEvent) {
        if ((e.getId()==R.id.city)&&(actionId == EditorInfo.IME_ACTION_DONE)) {
            if (binder.getCity().getName() != e.toString()) {
                String str = e.getText().toString();
                if (str != "") {
                    str += "&";
                } else {
                    str = "Sevastopol,ua&";
                }
                URL = getString(R.string.base_url);
                URL += str + getString(R.string.api_url);
                new LoadParseTask().execute(URL);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        long Bar = 5 * 24 * 60 * 60 * 1000 / 100 * seekBar.getProgress();
        Date date = new Date();
        binder.setDate(new Date(date.getTime()+Bar));
        binder.bind(city);
        }
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
}
