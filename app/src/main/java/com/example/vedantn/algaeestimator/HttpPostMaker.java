package com.example.vedantn.algaeestimator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
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
import android.widget.Toast;

/**
 * Created by vedant.n on 5/2/2015.
 */
public class HttpPostMaker extends Activity {

    final String SERVER_URL = "http://sage.cs.uwp.edu/algae/dbAdd.php";

    String datetimeStamp, description;
    Double algal,pbott,depth,surfaceTemp,bottomTemp,secchiDepth,dissolvedOxygen,latitude,longitude;

    public HttpPostMaker (String datetimeStamp,double algal,double pbott, double depth, double surfaceTemp, double bottomTemp,
                            double secchiDepth, double dissolvedOxygen, double latitude,double longitude, String description ){

        this.datetimeStamp = datetimeStamp;
        this.algal = algal;
        this.pbott = pbott;
        this.depth = depth;
        this.surfaceTemp = surfaceTemp;
        this.bottomTemp = bottomTemp;
        this.secchiDepth = secchiDepth;
        this.dissolvedOxygen = dissolvedOxygen;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;


    }


    public void postNow()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    HttpClient client  = new DefaultHttpClient();
                    URI uri = new URI(SERVER_URL);
                    HttpPost Newpost = new HttpPost(uri);
                    //HttpPost Newpost = new HttpPost(SERVER_URL);


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

                    Newpost.setEntity(new UrlEncodedFormEntity(pairs));
                    HttpResponse response = client.execute(Newpost);

                }

        catch(URISyntaxException e ){

            //Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();

        }

                catch(UnsupportedEncodingException e){

                    //Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
                catch(ClientProtocolException e){

                    //Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
                catch (IOException e){

                    //Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();

                }

            }
        }).start();


    }
}
