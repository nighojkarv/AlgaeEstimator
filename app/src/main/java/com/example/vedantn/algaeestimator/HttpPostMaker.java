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

    public void postData(){
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

    }
}
