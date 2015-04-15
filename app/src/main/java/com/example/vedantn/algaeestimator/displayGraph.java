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
    public double resultArrayGreen[];
    public double resultArrayBlueGreen[];
    public int arraySize;
    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.graph_display);
        //Get the result array and the size of time array
        Intent activityThatCalled = getIntent();
        resultArrayGreen = activityThatCalled.getExtras().getDoubleArray("resultArrayGreen");
        resultArrayBlueGreen = activityThatCalled.getExtras().getDoubleArray("resultArrayBlueGreen");
        arraySize = activityThatCalled.getExtras().getInt("arraySize");

        plot = (XYPlot) findViewById(R.id.graph);

        //Initialization of plotting arrays
        Number[] timeArray = new Number[arraySize];
        Number[] growthArray = new Number[arraySize];
        //Array for plotting the flat line
        Number[] flatLineArray = new Number[arraySize];

        //Converts double array to number array
        //Sets the time interval array
        //Sets the flat line array values to 40
        for(int i=0,j=0; i<arraySize;i++)
        {
            flatLineArray[i] = 40;
            growthArray[i] = resultArrayGreen[i];
            timeArray[i]=j;
            j+=24;
        }


        //Main series to be plotted
        XYSeries series = new SimpleXYSeries(
                Arrays.asList(timeArray),
                Arrays.asList(growthArray),
                "Algal Growth");

        //Series for the flat line
        XYSeries flatLine = new SimpleXYSeries(
                Arrays.asList(timeArray),
                Arrays.asList(flatLineArray),"Mean");

        //Formatter for the main series
        LineAndPointFormatter seriesFormat = new LineAndPointFormatter();
        //Formatter for the flat line
        LineAndPointFormatter formatter  = new LineAndPointFormatter(Color.RED,Color.TRANSPARENT,Color.TRANSPARENT,new PointLabelFormatter(Color.TRANSPARENT));

        //Sets the background graph
        plot.setDomainStep(XYStepMode.SUBDIVIDE,timeArray.length);
        plot.setRangeValueFormat(new DecimalFormat("0"));
        plot.setDomainValueFormat(new DecimalFormat("0"));
        plot.setDomainLabel("Hours");
        plot.setRangeLabel("mg/l");
        plot.setRangeTopMin(170);
        plot.getGraphWidget().getDomainOriginLabelPaint().setColor(Color.TRANSPARENT);

        //Plots main series and flat line
        plot.addSeries(series,seriesFormat);
        plot.addSeries(flatLine,formatter);



    }
}
