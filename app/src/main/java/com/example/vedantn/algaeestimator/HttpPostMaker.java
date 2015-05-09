package com.example.vedantn.algaeestimator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by vedant.n on 5/2/2015.
 */
public class HttpPostMaker extends Activity{

    final String SERVER_URL = "";

    HttpClient client  = new DefaultHttpClient();
    HttpPost post = new HttpPost(SERVER_URL);

    public void postData(String datetimeStamp,double algal,double pbott, double depth, double surfaceTemp, double bottomTemp,
                            double secchiDepth, double dissolvedOxygen, double latitude,double longitude, String description ){

        try {
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("datetimeStamp", datetimeStamp));
            pairs.add(new BasicNameValuePair("algal", String.valueOf(algal)));
            pairs.add(new BasicNameValuePair("pbott", String.valueOf(pbott)));
            pairs.add(new BasicNameValuePair("depth", String.valueOf(depth)));
            pairs.add(new BasicNameValuePair("surfaceTemp", String.valueOf(surfaceTemp)));
            pairs.add(new BasicNameValuePair("bottomTemp", String.valueOf(bottomTemp)));
            pairs.add(new BasicNameValuePair("secchiDepth", String.valueOf(secchiDepth)));
            pairs.add(new BasicNameValuePair("dissolvedOxygen", String.valueOf(dissolvedOxygen)));
            pairs.add(new BasicNameValuePair("latitude", String.valueOf(latitude)));
            pairs.add(new BasicNameValuePair("longitude", String.valueOf(longitude)));
            pairs.add(new BasicNameValuePair("description", description));

            post.setEntity(new UrlEncodedFormEntity(pairs));
        }
        catch(IOException e ){

        }

    }
}
