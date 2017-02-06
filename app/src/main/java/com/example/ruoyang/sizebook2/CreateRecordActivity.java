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
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

/*
 * In this activity, we can create a new record save it in recordList
 * And save the recordList in a gson file
 * If the back botton is clicked, jump bakc to the MainActivity
 */
public class CreateRecordActivity extends ActionBarActivity {
    //initialzation of all editText of record data
    private EditText nameText;
    private EditText dateText;
    private EditText neckText;
    private EditText bustText;
    private EditText waistText;
    private EditText hipText;
    private EditText chestText;
    private EditText inseamText;
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get intent from MainActivity
        setContentView(R.layout.activity_create_record);
        Intent intent = getIntent();
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

    //save all new data into a new record
    //And save the record into recordList
    //save the recordList into a gson file
    public void saveRecord(View view){
        EditText nameText = (EditText) findViewById(R.id.name);
        String name = nameText.getText().toString();
        if(name.trim().length()>0) {
            Record record = new Record(name);

            EditText dateText = (EditText) findViewById(R.id.date);
            String date = dateText.getText().toString();
            if (date.trim().length()>0)
                record.setDate(date);

            EditText neckText = (EditText) findViewById(R.id.neck);
            if(neckText.getText().toString().trim().length()>0){
                Double neck = Double.parseDouble(neckText.getText().toString());
                record.setNeck(neck);
            }

            EditText bustText = (EditText) findViewById(R.id.bust);
            if(bustText.getText().toString().trim().length()>0) {
                Double bust = Double.parseDouble(bustText.getText().toString());
                record.setBust(bust);
            }

            EditText waistText = (EditText) findViewById(R.id.waist);
            if(waistText.getText().toString().trim().length()>0) {
                Double waist = Double.parseDouble(waistText.getText().toString());
                record.setWaist(waist);
            }
            EditText hipText = (EditText) findViewById(R.id.hip);
            if(hipText.getText().toString().trim().length()>0) {
                Double hip = Double.parseDouble(hipText.getText().toString());
                record.setHip(hip);
            }
            EditText chestText = (EditText) findViewById(R.id.chest);
            if(chestText.getText().toString().trim().length()>0) {
                Double chest = Double.parseDouble(chestText.getText().toString());
                record.setChest(chest);
            }
            EditText inseamText = (EditText) findViewById(R.id.inseam);
            if(inseamText.getText().toString().trim().length()>0) {
                Double inseam = Double.parseDouble(inseamText.getText().toString());
                record.setInseam(inseam);
            }
            EditText commentText = (EditText) findViewById(R.id.comment);
            String comment = commentText.getText().toString();
            if (comment.trim().length()>0){
                record.setComment(comment);
            }


            MainActivity.recordList.add(record);
            saveInFile();
        }
        else{
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

    // if the bakc botton is clicked, jump back to MainActivity
    public void backToHome(View view){
        Intent e=new Intent(this, MainActivity.class);
        e.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(e);
    }

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
