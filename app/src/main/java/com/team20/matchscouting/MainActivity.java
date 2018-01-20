package com.team20.matchscouting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by JT on 1/7/18.
 */
public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void next(View view) {
        Intent intent = new Intent(MainActivity.this, ScoutScreen.class);

        EditText editFirst = (EditText) findViewById(R.id.editFirstName);
        String firstName = editFirst.getText().toString(); //First Name
        if (!firstName.equals("")){ //First Name is entered
            intent.putExtra("First_Name", firstName);

            EditText editLast = (EditText) findViewById(R.id.editLastName);
            String lastName = editLast.getText().toString(); //Last Name
            if (!lastName.equals("")){ //Last Name is entered
                intent.putExtra("Last_Name", lastName);

                EditText editMatch = (EditText) findViewById(R.id.editMatchNumber);
                String matchNumber = editMatch.getText().toString();
                if (!matchNumber.equals("")) { //Match number is entered
                    intent.putExtra("Match_Number", matchNumber);

                    //Add Alliance if statement

                    EditText editTeam = (EditText) findViewById(R.id.editTeam);
                    String teamNumber = editTeam.getText().toString();
                    if (!teamNumber.equals("")) { //Team Scouted is entered
                        intent.putExtra("Team_Number", teamNumber);

                        //If match is a replay
                        String replay = "";
                        CheckBox replayBox = (CheckBox) findViewById(R.id.editReplay);
                        replay += checkBox(replayBox, replay);
                        intent.putExtra("Replay_Match", replay);

                        startActivity(intent);
                    } else {
                        System.err.println("Team Number is not entered");
                        Toast.makeText(getApplicationContext(), "Team Number is not entered", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    System.err.println("Match Number is not entered");
                    Toast.makeText(getApplicationContext(), "Match Number is not entered", Toast.LENGTH_SHORT).show();
                }
            } else {
                System.err.println("Last Name is not entered");
                Toast.makeText(getApplicationContext(), "Last Name is not entered", Toast.LENGTH_SHORT).show();
            }
        } else {
            System.err.println("First Name is not entered");
            Toast.makeText(getApplicationContext(), "First Name is not entered", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    Do not change, stays the same every year
    Do not delete even if you do not end up using it
    Parameters: id tag of the checkbox, string of all checkboxes checked
    Needs string of all checkboxes because we have to separate all of the choices selected by a '|'
    Returns label of checkbox
    How to use:
    String cubeCollect = "";
    CheckBox portal = (CheckBox) findViewById(R.id.portal);
    cubeCollect += checkBox(portal, cubeCollect);
     */
    public String checkBox(CheckBox check, String total){
        if (check.isChecked()){
            String checkedBox = check.getText().toString(); //Box checked title
            if (total.equals("")){
                return checkedBox;
            } else {
                return "|" + checkedBox;
            }
        } else {
            return "";
        }
    }
}
