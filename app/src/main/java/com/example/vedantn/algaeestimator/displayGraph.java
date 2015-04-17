package com.example.vedantn.algaeestimator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.graphics.*;
import android.widget.Toast;
import android.widget.ToggleButton;

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

    XYSeries seriesGreen;
    XYSeries seriesBlueGreen;
    XYSeries flatLine;

    LineAndPointFormatter seriesFormatGreen;
    LineAndPointFormatter seriesFormatBlueGreen;
    LineAndPointFormatter formatter;

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
        Number timeArray[] = new Number[arraySize];
        Number growthArrayGreen[] = new Number[arraySize];
        Number growthArrayBlueGreen[] = new Number[arraySize];
        //Array for plotting the flat line
        Number[] flatLineArray = new Number[arraySize];

        //Converts double array to number array for Green and BlueGreen
        //Sets the time interval array
        //Sets the flat line array values to 40
        for(int i=0,j=0; i<arraySize;i++)
        {
            flatLineArray[i] = 40;
            growthArrayGreen[i] = resultArrayGreen[i];
            growthArrayBlueGreen[i] = resultArrayBlueGreen[i];
            timeArray[i]=j;
            j+=24;
        }


        //Green Algae series
        seriesGreen = new SimpleXYSeries(
                Arrays.asList(timeArray),
                Arrays.asList(growthArrayGreen),
                "Green Algae");

        //BlueGreen Algae series
        seriesBlueGreen = new SimpleXYSeries(
                Arrays.asList(timeArray),
                Arrays.asList(growthArrayBlueGreen),
                "Blue-Green Algae");


        //Series for the flat line
        flatLine = new SimpleXYSeries(
                Arrays.asList(timeArray),
                Arrays.asList(flatLineArray),"Mean");

        //Formatter for Algae series
        seriesFormatGreen = new LineAndPointFormatter(Color.GREEN,Color.YELLOW,Color.rgb(36,143,36),new PointLabelFormatter(Color.TRANSPARENT));
        seriesFormatBlueGreen = new LineAndPointFormatter();
        //Formatter for the flat line
        formatter  = new LineAndPointFormatter(Color.RED,Color.TRANSPARENT,Color.TRANSPARENT,new PointLabelFormatter(Color.TRANSPARENT));

        //Sets the background graph
        plot.setDomainStep(XYStepMode.SUBDIVIDE,timeArray.length);
        plot.setRangeValueFormat(new DecimalFormat("0"));
        plot.setDomainValueFormat(new DecimalFormat("0"));
        plot.setDomainLabel("Hours");
        plot.setRangeLabel("mg/l");
        plot.setRangeTopMin(170);
        plot.getGraphWidget().getDomainOriginLabelPaint().setColor(Color.TRANSPARENT);

        //Plots main series and flat line
        plot.addSeries(seriesGreen,seriesFormatGreen);
        plot.addSeries(seriesBlueGreen,seriesFormatBlueGreen);
        plot.addSeries(flatLine,formatter);


    }


}
