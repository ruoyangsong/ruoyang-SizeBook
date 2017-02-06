/*
 * Name: Ruoyang Song
 * CCID: ruoyang
 * Date: 2017.2.5
 * Copyright2017, Ruoyang Song, All rights reserved.
 */
package com.example.ruoyang.sizebook2;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
 * This is the main acticity of the project.
 * In this activity, I created a listView of oldRecordList and a button to add new record.
 * So If we can jump to CreateRecordActivity and ShowRecordActivity.
 */

public class MainActivity extends ActionBarActivity {
    public static final String FILENAME = "file.sav";   //name of file we save all the data
    private ListView oldRecordsList;                    //listView of all old records
    public static ArrayList<Record> recordList;         //the array of all records
    public static ArrayAdapter<Record> adapter;         //the adapter array
    private TextView countView;                         //the number of records
    public final static String EXTRA_MESSAGE = "com.example.ruoyang.sizebook2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_record, menu);
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

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        //we get the updated recordlist from loadFromFile and display it by adapter
        loadFromFile();
        oldRecordsList = (ListView) findViewById(R.id.oldRecordsList);
        adapter = new ArrayAdapter<Record>(this,
                R.layout.list_item, recordList);
        oldRecordsList.setAdapter(adapter);

        //we calculate the number of records and then display it by countView
        String count = String.valueOf(oldRecordsList.getAdapter().getCount());
        count = count+" records";
        countView = (TextView)findViewById(R.id.textView10);
        countView.setText(count);

        //we difine the click actionof a listView ,jump to ShowRecordActivity
        //and passing the position of the record in recordlist
        // by putExtra method
        oldRecordsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent2 = new Intent(getApplicationContext(),ShowRecordActivity.class);

                intent2.putExtra(EXTRA_MESSAGE, String.valueOf(position));
                startActivity(intent2);
            }
        });
    }


    //if the button is clicked, we jump to CreateRecordActivity by intent.
    public void addRecord(View view) {
        Intent intent = new Intent(this, CreateRecordActivity.class);
        startActivity(intent);
    }

    //we can get the updated recordlist from this method,
    //since the recordList is stores in a gson file.
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            recordList = gson.fromJson(in, new TypeToken<ArrayList<Record>>(){}.getType());

        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

}
