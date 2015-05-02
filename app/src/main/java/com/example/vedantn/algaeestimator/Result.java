package com.example.vedantn.algaeestimator;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
 * The Item class contains the three main attributes
 * that we expect the item to have for this application,
 * along with getters and setters for each of the
 * attributes.
 */
public class Result {
    // Class variables

   //TODO - Add a primary key to the table
    double algal;
    double pbott;
    double depth;
    double stemp;
    double bottemp;
    double sd;
    double _do;
    double lon;
    double lat;
    String desc;
    String datetime;


    public Result() {
    }

    // Constructor for application
    public Result(double alg, double pb, double dep, double stem,
                  double bot, double s, double d, double longi, double lati, String des, String dtime) {
        this.algal = alg;
        this.pbott = pb;
        this.depth = dep;
        this.stemp = stem;
        this.bottemp = bot;
        this.sd = s;
        this._do = d;
        this.lon = longi;
        this.lat = lati;
        this.desc = des;
        this.dateTime = dtime;
    }

    public double getAlgal(){
        return this.algal;
    }

    public void setAlgal(double a){
        this.algal = a;
    }

    public double getPbott(){
        return this.pbott;
    }

    public void setPbott(double p){
        this.pbott = p;
    }

    public double getDepth(){
        return this.depth;
    }
    public void setDepth(double d){
        this.depth = d;
    }

    public double getStemp(){
        return this.stemp;
    }
    public void setStemp(double s){
        this.stemp = s;
    }

    public double getBotTemp(){
        return this.bottemp;
    }
    public void setBotTemp(double b){
        this.bottemp = b;
    }

    public double getSd(){
        return this.sd;
    }
    public void setSd(double s){
        this.sd = s;
    }

    public double getDo(){
        return this._do;
    }
    public void setDo(double d){
        this._do = d;
    }
    
    public double getLong(){
        if(this.lon!=null)
            return this.lon;
        return 0.0;
    }
    public void setLong(double l){
        this.lon = l;
    }
    
    public double getLat(){
        if(this.lat!=null)
            return this.lat;
        return 0.0;
    }
    public void setLat(double l){
        this.lat = l;
    }
    public String getDescription(){
        if(desc!=null)
            return this.desc;
        return "";
    }
    public void setDescription(String d){
        if(d!=null)
            this.desc = d;
        this.desc = "";
    }
    
    public String getDateTime(){
        return this.dateTime;
    }
    public void setDateTime(){
        this.dateTime = dateTimeHelper();
    }
    
    public String dateTimeHelper(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return (dateFormat.format(date));
    }
}
