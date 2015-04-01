package com.example.vedantn.algaeestimator;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import org.apache.commons.logging.Log;
import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    //Constants
    public double r01 = 0.065;
    public double r02 = 0.034;
    public double r03 = 0.01;
    public double kCHL = 150;
    public double kCHLY = 100;
    final int PREDICTION_DAYS = 9;
    final double mathematicalE = 2.718281828459045;


    //Variable Declaration

    //Input Values from user
    public double valueOfAlgal,pBott,depth,sTemp,botTemp,sD,dO;

    //Calculated Values
    public double n0,r0,pav,tempDiff,k,nT;
    public double [] answerArray = new double[PREDICTION_DAYS];

    //Widget Declaration
    Spinner calcMethodSpinner;

    /*Spinner spinnerDepth;
    Spinner spinnerStemp;
    Spinner spinnerBotTemp;
    Spinner spinnerSD;
*/
    EditText tbValueOfAlgal;
    EditText tbPbott;
    EditText tbDepth;
    EditText tbStemp;
    EditText tbBotTemp;
    EditText tbSD;
    EditText tbDO;
    TextView lblTestLocation;

    //GPS Variables
    public static double userLat, userLon;
    public static Location location = new Location(LocationManager.GPS_PROVIDER);
    LocationManager locationManager;
    LocationListener locationListener;
    //Variable Declaration Done


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calcMethodSpinner = (Spinner) findViewById(R.id.calcMethodSpinner);
        ArrayAdapter adapterCalcMethod = ArrayAdapter.createFromResource(this,R.array.calcMethod,android.R.layout.simple_spinner_dropdown_item);
        calcMethodSpinner.setAdapter(adapterCalcMethod);
        calcMethodSpinner.setOnItemSelectedListener(this);

        //spinnerDepth = (Spinner) findViewById(R.id.spinnerDepth);
        //ArrayAdapter adapterDepth = ArrayAdapter.createFromResource(this,R.array.depthMeasure,android.R.layout.simple_spinner_dropdown_item);
        //spinnerDepth.setAdapter(adapterDepth);
        //spinnerDepth.setOnItemSelectedListener(this);

        tbValueOfAlgal = (EditText) findViewById(R.id.tbValueOfAlgal);
        tbPbott = (EditText) findViewById(R.id.tbPbott);
        tbDepth = (EditText) findViewById(R.id.tbDepth);
        tbStemp = (EditText) findViewById(R.id.tbStemp);
        tbBotTemp = (EditText) findViewById(R.id.tbBotTemp);
        tbSD = (EditText) findViewById(R.id.tbSD);
        tbDO = (EditText) findViewById(R.id.tbDO);
        lblTestLocation = (TextView) findViewById(R.id.lblTestLocation);

        //location.setLatitude(42.643213);
        //location.setLongitude(-87.847922);
        location.setLatitude(40.643213);
        location.setLongitude(-80.847922);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

               if(location != null) {
                   userLat = location.getLatitude();
                   userLon = location.getLongitude();
                   Toast.makeText(MainActivity.this,"Location Changed",Toast.LENGTH_LONG).show();
                   lblTestLocation.setText("Location Changed \n" + userLat +"\n" + userLon);

               }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,60000,5,locationListener);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id==R.id.exit_the_app)
        {
            DialogFragment exitFragment = new ExitDialog();
            exitFragment.show(getFragmentManager(),"exitDialog");

        }

        return super.onOptionsItemSelected(item);
    }


    //Select calculation mode
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TextView selection = (TextView) view;
        Toast.makeText(this,selection.getText(),Toast.LENGTH_SHORT).show();

        //For making unused components invisble from the main page when calculation mode is selected
        switch (position)
        {
            case 0:
                {
                    resetView();
                    ((TextView) findViewById(R.id.lblValueOfAlgal)).setText("Total Chl a(mg/l)");

                    ((TextView) findViewById(R.id.lblSD)).setVisibility(View.INVISIBLE);
                    ((EditText) findViewById(R.id.tbSD)).setVisibility(View.INVISIBLE);
                    ((Spinner) findViewById(R.id.spinnerSD)).setVisibility(View.INVISIBLE);


                    ((TextView) findViewById(R.id.lblDO)).setVisibility(View.INVISIBLE);
                    ((EditText) findViewById(R.id.tbDO)).setVisibility(View.INVISIBLE);
                    break;

                }

            case 1:
            {
                resetView();
                ((TextView) findViewById(R.id.lblValueOfAlgal)).setVisibility(View.INVISIBLE);
                ((EditText) findViewById(R.id.tbValueOfAlgal)).setVisibility(View.INVISIBLE);

                break;
            }

            case 2:
            {
                resetView();
                ((TextView) findViewById(R.id.lblValueOfAlgal)).setText("Cyno Chl a(μg/l)");

                ((TextView) findViewById(R.id.lblSD)).setVisibility(View.INVISIBLE);
                ((EditText) findViewById(R.id.tbSD)).setVisibility(View.INVISIBLE);
                ((Spinner) findViewById(R.id.spinnerSD)).setVisibility(View.INVISIBLE);

                ((TextView) findViewById(R.id.lblDO)).setVisibility(View.INVISIBLE);
                ((EditText) findViewById(R.id.tbDO)).setVisibility(View.INVISIBLE);
                break;
            }

            case 3:
            {

                resetView();
                ((TextView) findViewById(R.id.lblValueOfAlgal)).setVisibility(View.INVISIBLE);
                ((EditText) findViewById(R.id.tbValueOfAlgal)).setVisibility(View.INVISIBLE);

                ((TextView) findViewById(R.id.lblDO)).setVisibility(View.INVISIBLE);
                ((EditText) findViewById(R.id.tbDO)).setVisibility(View.INVISIBLE);
                break;

            }
            default:
            {
                resetView();
            }
        }//Switch


    }
    //Make all the components visible on the main page.

    //Sets the current co-ordinates


    public void resetView()
    {
        ((TextView) findViewById(R.id.lblValueOfAlgal)).setText("Value of Algal (µg/ml)");

        ((TextView) findViewById(R.id.lblValueOfAlgal)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.tbValueOfAlgal)).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.lblPbott)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.tbPbott)).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.lblDepth)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.tbDepth)).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.lblStemp)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.tbStemp)).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.lblBotTemp)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.tbBotTemp)).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.lblSD)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.tbSD)).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.lblDO)).setVisibility(View.VISIBLE);
        ((EditText) findViewById(R.id.tbDO)).setVisibility(View.VISIBLE);

        ((Spinner) findViewById(R.id.spinnerDepth)).setVisibility(View.VISIBLE);
        ((Spinner) findViewById(R.id.spinnerStemp)).setVisibility(View.VISIBLE);
        ((Spinner) findViewById(R.id.spinnerBotTemp)).setVisibility(View.VISIBLE);
        ((Spinner) findViewById(R.id.spinnerSD)).setVisibility(View.VISIBLE);



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    //Method to clear the Text Boxes on the main page
    public void methodClear(View view) {

        ((EditText) findViewById(R.id.tbValueOfAlgal)).setText("");
        ((EditText) findViewById(R.id.tbPbott)).setText("");
        ((EditText) findViewById(R.id.tbDepth)).setText("");
        ((EditText) findViewById(R.id.tbStemp)).setText("");
        ((EditText) findViewById(R.id.tbBotTemp)).setText("");
        ((EditText) findViewById(R.id.tbSD)).setText("");
        ((EditText) findViewById(R.id.tbDO)).setText("");



    }

    //Calculation using Verhulst equation
    public void methodCalculate(View view)
    {
        setValues();
        finalCalculation();

        displayResults();
        //setZero();

        //Intent to go to Results Activity
        Intent goToDisplayScreenIntent = new Intent(MainActivity.this,resultScreen.class);
        final int result =1;
        //Adding result array size and result array to intent
        goToDisplayScreenIntent.putExtra("resultArray",answerArray);
        goToDisplayScreenIntent.putExtra("arraySize",PREDICTION_DAYS);
        //Opening result activity
        startActivityForResult(goToDisplayScreenIntent,result);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //String tst = data.getStringExtra("testResult");
        //Toast.makeText(this,tst,Toast.LENGTH_LONG).show();
    }

    //Sets values for all ser inputs and calculated values
    public void setValues() {

        try {

            //Get values from main activity for user inputs
            //Set Value of Algal
            if(tbValueOfAlgal.getText().toString().isEmpty())
            {
                valueOfAlgal = 0;
            }
            else {
                valueOfAlgal = Double.parseDouble(tbValueOfAlgal.getText().toString());
            }

            //Set Pbott
            if(tbPbott.getText().toString().isEmpty())
            {
                pBott =0;
            }
            else {
                pBott = Double.parseDouble(tbPbott.getText().toString());
            }
            //Set DO
            if(tbDO.getText().toString().isEmpty())
            {
                dO =0;
            }
            else {
                dO = Double.parseDouble(tbDO.getText().toString());
            }
            //Set depth
            if(tbDepth.getText().toString().isEmpty())
            {
               depth = 0;
            }
            else
            {
                depth = Double.parseDouble(tbDepth.getText().toString());
            }
                //Convert depth to meters
            Spinner tempDepth = (Spinner) findViewById(R.id.spinnerDepth);
            if (tempDepth.getSelectedItem().toString()=="ft") {
                depth = ftTOm(depth);

            }

            //Set sTemp
            if(tbStemp.getText().toString().isEmpty())
            {
                sTemp = 0;
            }
            else {
                sTemp = Double.parseDouble(tbStemp.getText().toString());
            }
                //Convert sTemp to Celcius
            Spinner tempStemp = (Spinner) findViewById(R.id.spinnerStemp);
            if ((String) tempStemp.getSelectedItem().toString() == "°F") {
                sTemp = fTOc(sTemp);
            }

            //Set botTemp
            if(tbBotTemp.getText().toString().isEmpty())
            {
                botTemp = 0;
            }
            else {
                botTemp = Double.parseDouble(tbBotTemp.getText().toString());
            }
                //Convert botTemp to Celcius
            Spinner tempBotTemp = (Spinner) findViewById(R.id.spinnerBotTemp);
            if ((String) tempBotTemp.getSelectedItem().toString() == "°F") {
                botTemp = fTOc(botTemp);
            }

            //Set SD
            if(tbSD.getText().toString().isEmpty())
            {
                sD = 0;
            }
            else {
                sD = Double.parseDouble(tbSD.getText().toString());
            }
                //Convert SD to meters
            Spinner tempSD = (Spinner) findViewById(R.id.spinnerSD);
            if ((String) tempSD.getSelectedItem().toString() == "ft") {
                sD = ftTOm(sD);
            }

            // Sets all calculated values

            //Set pav
            pav=pBott/depth;

            //Set tempDiff
            tempDiff = sTemp-botTemp;

            //Set n0 and k
            switch(calcMethodSpinner.getSelectedItemPosition())
            {
                case 0:
                {
                    n0 = valueOfAlgal;
                    k=kCHL;
                    break;
                }
                case 1:
                {
                    n0 = -6.4775+(21.6396*(1/sD))+0.0006*dO*dO;
                    k=kCHL;
                    break;
                }
                case 2:
                {
                    n0=valueOfAlgal;
                    k=kCHLY;
                    break;
                }
                case 3:
                {
                    n0=0.409-(0.7486*sTemp)+(17.6979*(1/sD));
                    k=kCHLY;
                    break;
                }
            }//Set n0

            //Set r0
            if(sTemp>15 && pav > 0.02 && tempDiff <4)
            {
                if(tempDiff > 1)
                {
                    r0 = r02;
                }
                else
                {
                    r0 = r01;
                }
            }
            else //If the condition is not satisfied, there is no algal growth
            {
                Toast.makeText(this,getString(R.string.no_algal_growth),Toast.LENGTH_SHORT).show();
            }//Set r0

            //Set k
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
            //No Action on Exception
        }

    }

    //Conversion from Feet to Meters
    public double ftTOm(double meters)
    {
        meters = meters / 3.2808;
        return meters;

    }

    //Conversion from Faranhite to Celsius
    public double fTOc(double faranhite)
    {
        faranhite = (faranhite-32)*(5/9);
        return faranhite;

    }

    public void finalCalculation()
    {
       int time=0;
       for(int i=0;i<PREDICTION_DAYS; i++)
       {

           nT = (n0*k)/(n0+((k-n0)*Math.pow(mathematicalE,(-r0*time))));

           answerArray[i] = nT;
           time+=24;
           //Toast.makeText(this,Double.toString(answerArray[2]),Toast.LENGTH_LONG).show();

       }
    }

    /*Sets all text box values to 0
    This function is used to set the values of all text boxes to 0 after every calculation
    */
    public void setZero()
    {
        tbValueOfAlgal.setText("");
        tbPbott.setText("");
        tbDepth.setText("");
        tbStemp.setText("");
        tbBotTemp.setText("");
        tbSD.setText("");
        tbDO.setText("");

    }


    public void displayResults()
    {
        /*
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,0,locationListener);
        userLat = location.getLatitude();
        userLon = location.getLongitude();

        String str = String.valueOf(userLat) + String.valueOf(userLon);
        lblTestLocation.append(str);
*/

    }

}
