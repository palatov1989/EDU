package com.test.weather;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownLoadXMLTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... path) {

        String content;
        try{
            content = getContent(path[0]);
        }
        catch (IOException ex){
            content = ex.getMessage();
        }

        return content;
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
}