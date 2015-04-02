package com.example.vedantn.algaeestimator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.graphics.*;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import com.androidplot.Plot;

import java.util.Arrays;
import java.text.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by vedant.n on 4/2/2015.
 */
public class displayGraph extends Activity {

    //Initialization
    public double resultArray[];
    public int arraySize;
    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.graph_display);
        //Get the result array and the size of time array
        Intent activityThatCalled = getIntent();
        resultArray = activityThatCalled.getExtras().getDoubleArray("resultArray");
        arraySize = activityThatCalled.getExtras().getInt("arraySize");

        plot = (XYPlot) findViewById(R.id.graph);
        //Initialize time and result arrays
        Number[] timeArray = new Number[arraySize];
        Number[] growthArray = new Number[arraySize];
        for(int i=0,j=0; i<arraySize;i++)
        {
            growthArray[i] = resultArray[i];
            timeArray[i]=j;
            j+=24;
        }

        XYSeries series2 = new SimpleXYSeries(
                Arrays.asList(timeArray),
                Arrays.asList(growthArray),
                "Algal Growth");

        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        LineAndPointFormatter formatter  = new LineAndPointFormatter();

        plot.setDomainStep(XYStepMode.SUBDIVIDE,timeArray.length);
        plot.setRangeValueFormat(new DecimalFormat("0"));
        plot.addSeries(series2,series2Format);



    }
}
