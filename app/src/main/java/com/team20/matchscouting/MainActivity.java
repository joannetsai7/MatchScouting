package com.team20.matchscouting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by JT on 1/7/18.
 */
public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextButtonOnClick(View view){
        //Get First Name
        EditText FirstNameEditText = (EditText) findViewById(R.id.editFirstName);
        String firstName = FirstNameEditText.getText().toString();

        //Get Last Name
        EditText LastNameEditText = (EditText) findViewById(R.id.editLastName);
        String lastName = LastNameEditText.getText().toString();

        //Get Match Number
        //EditText MatchNumberEditText = (EditText) findViewById(R.id.editMatch);
        //int matchNumber = Integer.valueOf(MatchNumberEditText.getText().toString());

        //Get Team Number
        //EditText TeamNumberEditText = (EditText) findViewById(R.id.editTeam);
        //int teamNumber = Integer.valueOf((MatchNumberEditText.getText().toString()));

        //

        Intent intent = new Intent(MainActivity.this,ScoutScreen.class);

        startActivity(intent);
    }
}
