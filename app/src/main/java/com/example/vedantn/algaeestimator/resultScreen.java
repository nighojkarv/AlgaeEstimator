package com.example.vedantn.algaeestimator;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by vedant.n on 3/23/2015.
 */
public class resultScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_display);
        Intent activityThatCalled = getIntent();

        //Gets the result array size from calling Intent
        final int arraySize = activityThatCalled.getExtras().getInt("arraySize");
        double[] resultArray = new double[arraySize];

        //Gets the result array from the main activity
        resultArray = activityThatCalled.getExtras().getDoubleArray("resultArray");

        //Displays the result this screen
        TextView lblDisplayResults = (TextView) findViewById(R.id.lblDisplayResults);
        lblDisplayResults.append("\n\n");
        String str;
        int hours = 0;

        DecimalFormat myFormat = new DecimalFormat("#.##");
        for(int i=0;i<arraySize;i++)
        {
            str = String.valueOf(hours) + " hours : " + String.valueOf(String.format("%.2f",resultArray[i])) + "\n\n";
            lblDisplayResults.append(str);
            hours +=24;
        }


    }


    public void goBack(View view) {

        //String testRes = "Success";
        Intent goingBack = new Intent();
        //goingBack.putExtra("testResult",testRes);
        setResult(RESULT_OK, goingBack);
        finish();
    }
}
