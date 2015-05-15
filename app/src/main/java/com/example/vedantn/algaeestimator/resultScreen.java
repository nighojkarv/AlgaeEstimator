/*
*
*  Copyright 2015 University of Wisconsin - Parkside
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*
*
*/
package com.example.vedantn.algaeestimator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by vedant.n on 3/23/2015.
 */
public class resultScreen extends Activity {

    public double[] resultArrayGreen;
    public double[] resultArrayBlueGreen;
    public int arraySize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_display);
        Intent activityThatCalled = getIntent();

        //Gets the result array size from calling Intent
        arraySize = activityThatCalled.getExtras().getInt("arraySize");
        resultArrayGreen = new double[arraySize];
        resultArrayBlueGreen = new double[arraySize];

        //Gets the result array from the main activity
        resultArrayGreen = activityThatCalled.getExtras().getDoubleArray("resultArrayGreen");
        resultArrayBlueGreen = activityThatCalled.getExtras().getDoubleArray("resultArrayBlueGreen");

        //Displays the result on the screen
        TextView lblDisplayResultsGreen = (TextView) findViewById(R.id.lblDisplayResultsGreen);
        TextView lblDisplayResultsBlueGreen = (TextView) findViewById(R.id.lblDisplayResultsBlueGreen);
        lblDisplayResultsBlueGreen.append("\n");
        lblDisplayResultsGreen.append("\n");
        String strGreen, strBlueGreen;

        int hours = 0;

        DecimalFormat myFormat = new DecimalFormat("#.##");
        for(int i=0;i<arraySize;i++)
        {
            strGreen = String.valueOf(hours) + " hours : " + String.valueOf(String.format("%.2f",resultArrayGreen[i])) +" (mg/l)" + "\n";
            lblDisplayResultsGreen.append(strGreen);

            strBlueGreen = String.valueOf(hours) + " hours : " + String.valueOf(String.format("%.2f",resultArrayBlueGreen[i])) +" (mg/l)" + "\n";
            lblDisplayResultsBlueGreen.append(strBlueGreen);

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

    public void openGraph(View view){
        //Intent to go to Results Activity
        Intent goToGraphScreenIntent = new Intent(resultScreen.this,displayGraph.class);
        final int result =1;
        //Adding result array size and result array to intent
        goToGraphScreenIntent.putExtra("resultArrayGreen",resultArrayGreen);
        goToGraphScreenIntent.putExtra("resultArrayBlueGreen",resultArrayBlueGreen);
        goToGraphScreenIntent.putExtra("arraySize",arraySize);
        //Opening result activity
        startActivityForResult(goToGraphScreenIntent,result);

    }
}
