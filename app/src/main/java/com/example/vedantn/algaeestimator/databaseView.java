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
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;
import java.util.List;

/**
 * Created by vedant.n on 4/27/2015.
 */
public class databaseView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.database_view);

        //FIXME - Integrate database. Data is not getting added to the local database.

        final DatabaseHandler dbh = new DatabaseHandler(this);
        List<Result> resultList = dbh.getAllResults();

        String itemsInList[] = new String[resultList.size()];


        //TEMP - This array is for testing. This array will contain values from the database.
        for(int i=0;i<resultList.size();i++)
        {

            itemsInList[i] =  resultList.get(i).getDateTime();


        }
        //String itemsInList[] = {"5/3/2015 13:27:36","5/5/2015 11:23:16","5/6/2015 19:17:53"};
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

                    dbh.deleteAll();
                    }
                });
                deleteDialog.show();

                return true;
            }
        });



    }
}
