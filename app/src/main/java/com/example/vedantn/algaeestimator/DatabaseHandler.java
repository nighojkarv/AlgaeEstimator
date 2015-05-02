package com.example.vedantn.algaeestimator;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vedantn.algaeestimator.Result;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "algaeEstimator";

    // Items table name
    private static final String TABLE_NAME = "results";

    // Attribute names
    //TODO - Add primary key and date time stamp to the table
    private static final String PRIMARY_KEY = "pk";
    private static final String ALGAL = "algal";
    private static final String P_BOTT = "pbott";
    private static final String DEPTH = "depth";
    private static final String S_TEMP = "stemp";
    private static final String BOT_TEMP = "bottemp";
    private static final String SD = "sd";
    private static final String DO = "do";
    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lon";
    private static final String DESCRIPTION ="desc";
    private static final String DATE_TIME = "datetime";

    // Default constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating a table within the Database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // make the table
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + PRIMARY_KEY + "INTEGER, " +
                DATE_TIME + " NUMERIC, " + ALGAL + " REAL, " + P_BOTT + " REAL, " + DEPTH + " REAL, "
                + S_TEMP + " REAL, " + BOT_TEMP + " REAL, " + SD + " REAL, "
                + DO + " REAL, " + LATITUDE + " REAL, " + LONGITUDE + " REAL, " + DESCRIPTION + "TEXT" + ")";
        db.execSQL(CREATE_TABLE); // send query
    }

    /*
     * NOT YET IMPLEMENTED
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    /*
     * Adds a new Result to the database.
     */
    public void addResult(Result result) {
        if (result != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(ALGAL, result.getAlgal());
            values.put(P_BOTT, result.getPbott());
            values.put(DEPTH, result.getDepth());
            values.put(S_TEMP, result.getStemp());
            values.put(BOT_TEMP, result.getBotTemp());
            values.put(SD, result.getSd());
            values.put(DO, result.getDo());
            // Inserting Row
            db.insert(TABLE_NAME, null, values);
            db.close(); // Closing database connection
        }
    }

    /*
     * Returns an ArrayList of <Item> containing all of the items within the
     * database.
     */

    public List<Result> getAllResults(){
        List<Result> resultList = new ArrayList<Result>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do{
                Result res = new Result();
                res.setAlgal(Double.parseDouble(cursor.getString(0)));
                res.setPbott(Double.parseDouble(cursor.getString(1)));
                res.setDepth(Double.parseDouble(cursor.getString(2)));
                res.setStemp(Double.parseDouble(cursor.getString(3)));
                res.setBotTemp(Double.parseDouble(cursor.getString(4)));
                res.setSd(Double.parseDouble(cursor.getString(5)));
                res.setDo(Double.parseDouble(cursor.getString(6)));
                resultList.add(res);
            } while (cursor.moveToNext());
        }
        return resultList;
    }

    /*
     * Deletes every item in the database by dropping the table.
     */
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);

    }
}