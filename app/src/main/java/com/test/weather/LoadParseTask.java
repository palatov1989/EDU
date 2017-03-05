package com.test.weather;
import android.os.AsyncTask;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoadParseTask extends AsyncTask<String, Void, View> {
    ViewBinder binder;
    View activity;

    public LoadParseTask(View v) {
        this.activity = v;
    }

    @Override
    protected View doInBackground(String... str) {
        String content;
        try{
            content = getContent(str[0]);
        }
        catch (IOException ex){
            content = ex.getMessage();
        }
        binder = new ViewBinder(content);
        return (activity);
    }

    private String getContent(String path) throws IOException {
        BufferedReader reader=null;
        try {
            URL url=new URL(path);
            HttpURLConnection c     = (HttpURLConnection)   url.openConnection();
            c.setRequestMethod("GET");
            c.setReadTimeout(10000);
            c.connect();
            reader= new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder buf=new StringBuilder();
            String line=null;
            while ((line=reader.readLine()) != null) {
                buf.append(line + "\n");
            }
            return(buf.toString());
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    protected void onPostExecute(View result) {
    binder.bind(activity);
    }


}