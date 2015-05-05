package com.example.vedantn.algaeestimator;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vedant.n on 4/27/2015.
 */
public class databaseView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.database_view);

        //TODO - Integrate database
        List<Result> resultList = new ArrayList<Result>();
        DatabaseHandler dbh = new DatabaseHandler(this);

        //TODO - Add Datetime to the TEMP array
        resultList = dbh.getAllResults();

        //TEMP - This array is for testing. This array will contain values from the database.
        String itemsInList[] = {"Alpha","Beta","Gamma","Delta","Epsilon","1","2","3","4","5"};
        ListAdapter listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,itemsInList);

        ListView dataListView = (ListView) findViewById(R.id.dataListView);
        dataListView.setAdapter(listAdapter);
        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selection = String.valueOf(parent.getItemAtPosition(position));

                //TODO - On click, load the values from the db and fill home screen
                Toast.makeText(databaseView.this,selection,Toast.LENGTH_LONG).show();
            }
        });



        dataListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog deleteDialog = new AlertDialog.Builder(databaseView.this).create();
                deleteDialog.setTitle("Confirm Delete");
                deleteDialog.setMessage("Are you sure you want to delete this data?");
                deleteDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //TODO - On click, delete values from list and db.

                    }
                });
                deleteDialog.show();

                return true;
            }
        });



    }
}
