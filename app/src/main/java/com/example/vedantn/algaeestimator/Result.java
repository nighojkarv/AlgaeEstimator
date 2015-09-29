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
    String dateTime;
    final String DATE_TIME_FORMAT = "MM-dd-yyyy HH:mm:ss";

    public Result() {
    }

    // Constructor for application
    public Result(double alg, double pb, double dep, double stem,
                  double bot, double s, double d, double lati, double longi, String des, String dtime) {
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
        if(this.lon !=0)
            return this.lon;
        return 0.0;
    }
    public void setLong(double l){
        this.lon = l;
    }

    public double getLat(){
        if(this.lat!=0)
            return this.lat;
        return 0.0;
    }
    public void setLat(double l){
        this.lat = l;
    }

    public String getDescription(){
        if(desc!=null)
            return this.desc;
        return "null";
    }
    public void setDescription(String d){
        if(d!=null)
            this.desc = d;
        this.desc = "";
    }
    
    public String getDateTime(){
        return this.dateTime;
    }
    public void setDateTime(String d){

        this.dateTime = d;

    }
    
    public String dateTimeHelper(){
        DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date = new Date();
        return (dateFormat.format(date));
    }
}
