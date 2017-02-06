/*
 * Name: Ruoyang Song
 * CCID: ruoyang
 * Date: 2017.2.5
 * Copyright2017, Ruoyang Song, All rights reserved.
 */
package com.example.ruoyang.sizebook2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * This is the ShowRecordActivity.
 * In this activity, we can show the detail of a record.
 * And if the edit botton is clicked, jump to EditRecordActivity
 * if the back botton is clicked, jump to MainActivity
 */
public class ShowRecordActivity extends ActionBarActivity {
    public final static String EXTRA_MESSAGE1 = "com.example.ruoyang.sizebook2";

    //initialization of all the textview for record class
    private TextView nameView;
    private TextView DateView;
    private TextView NeckView;
    private TextView BustView;
    private TextView WaistView;
    private TextView HipView;
    private TextView ChestView;
    private TextView InseamView;
    private TextView CommentView;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);

        //we get the record position passing from MainActivity
        //and use this position to loacte the record we need to show detail
        Intent intent2 = getIntent();
        String message = intent2.getStringExtra(MainActivity.EXTRA_MESSAGE);
        position = Integer.parseInt(message);

        //get all the record class data and display them
        nameView=(TextView)findViewById(R.id.textView20);
        nameView.setText(MainActivity.recordList.get(position).getName());

        DateView=(TextView)findViewById(R.id.textView21);
        DateView.setText(MainActivity.recordList.get(position).getDate());

        NeckView=(TextView)findViewById(R.id.textView22);
        NeckView.setText(Double.toString(MainActivity.recordList.get(position).getNeck()));

        BustView=(TextView)findViewById(R.id.textView23);
        BustView.setText(Double.toString(MainActivity.recordList.get(position).getBust()));

        WaistView=(TextView)findViewById(R.id.textView24);
        WaistView.setText(Double.toString(MainActivity.recordList.get(position).getWaist()));

        HipView=(TextView)findViewById(R.id.textView25);
        HipView.setText(Double.toString(MainActivity.recordList.get(position).getHip()));

        ChestView=(TextView)findViewById(R.id.textView26);
        ChestView.setText(Double.toString(MainActivity.recordList.get(position).getChest()));

        InseamView=(TextView)findViewById(R.id.textView27);
        InseamView.setText(Double.toString(MainActivity.recordList.get(position).getInseam()));

        CommentView = (TextView)findViewById(R.id.textView28);
        CommentView.setText(MainActivity.recordList.get(position).getComment());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_record, menu);
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

    //If the delete button is pressed, delete the record in recordlist
    //And then jump back to the MainActivity
    public void deleteRecord(View view){
        MainActivity.recordList.remove(position);
        saveInFile();

        Intent a=new Intent(this, MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);

    }

    //If the bakc button is clicked, jump back to MainActivity
    public void backToMain(View view){
        Intent b=new Intent(this, MainActivity.class);
        b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(b);
    }

    //If the edit button is clicked, jump to EditRecordActivity
    //Passing the position of current record
    public void editRecord(View view){
        Intent intent3 = new Intent(getApplicationContext(),EditRecordActivity.class);
        intent3.putExtra(EXTRA_MESSAGE1, String.valueOf(position));
        startActivity(intent3);

    }

    //save the recordlist in the gson file
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(MainActivity.FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(MainActivity.recordList, out);

            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


}
