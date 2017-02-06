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

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * In this activity, we change the data of a record and save the new
 * data into the current record.
 * If the back button is clicked, jump back to MainActivity
 */
public class EditRecordActivity extends ActionBarActivity {
    //initialization of all editText
    private int position;
    private EditText nameText;
    private EditText dateText;
    private EditText neckText;
    private EditText bustText;
    private EditText waistText;
    private EditText hipText;
    private EditText chestText;
    private EditText inseamText;
    private EditText commentText;
    private Record record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        //get the record postion passing from ShowRecordActivity
        //and get the current record
        Intent intent3 = getIntent();
        String message = intent3.getStringExtra(ShowRecordActivity.EXTRA_MESSAGE1);
        position = Integer.parseInt(message);
        record = MainActivity.recordList.get(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_record, menu);
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

    // if the save button is clicked, we update all the new data
    //to the current record. Save the record in a json file
    //and jump bakc to the MainActivity
    public void saveNewRecord(View view){
        EditText nameText = (EditText) findViewById(R.id.editText);
        String name = nameText.getText().toString();
        if(name.trim().length()>0) {
            record.setName(name);
        }

        EditText dateText = (EditText) findViewById(R.id.editText2);
        String date = dateText.getText().toString();
        if (date.trim().length()>0)
            record.setDate(date);

        EditText neckText = (EditText) findViewById(R.id.editText3);
        if(neckText.getText().toString().trim().length()>0){
            Double neck = Double.parseDouble(neckText.getText().toString());
            record.setNeck(neck);
        }

        EditText bustText = (EditText) findViewById(R.id.editText4);
        if(bustText.getText().toString().trim().length()>0) {
            Double bust = Double.parseDouble(bustText.getText().toString());
            record.setBust(bust);
        }

        EditText waistText = (EditText) findViewById(R.id.editText5);
        if(waistText.getText().toString().trim().length()>0) {
            Double waist = Double.parseDouble(waistText.getText().toString());
            record.setWaist(waist);
        }
        EditText hipText = (EditText) findViewById(R.id.editText6);
        if(hipText.getText().toString().trim().length()>0) {
            Double hip = Double.parseDouble(hipText.getText().toString());
            record.setHip(hip);
        }
        EditText chestText = (EditText) findViewById(R.id.editText7);
        if(chestText.getText().toString().trim().length()>0) {
            Double chest = Double.parseDouble(chestText.getText().toString());
            record.setChest(chest);
        }
        EditText inseamText = (EditText) findViewById(R.id.editText8);
        if(inseamText.getText().toString().trim().length()>0) {
            Double inseam = Double.parseDouble(inseamText.getText().toString());
            record.setInseam(inseam);
        }
        EditText commentText = (EditText) findViewById(R.id.editText9);
        String comment = commentText.getText().toString();
        if (comment.trim().length()>0){
            record.setComment(comment);
        }
        saveInFile();
        Intent c=new Intent(this, MainActivity.class);
        c.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(c);

    }

    //if the back button is clicked, jump bakc to the Main Activity
    public void backHome(View view){
        Intent d=new Intent(this, MainActivity.class);
        d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(d);
    }

    //Save the recordList into a gson file.
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
