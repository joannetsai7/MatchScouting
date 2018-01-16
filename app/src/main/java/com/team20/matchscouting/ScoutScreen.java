package com.team20.matchscouting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Ben on 1/7/2018.
 */

public class ScoutScreen extends Activity{
    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.scout_screen);
    }

    public void subtract(View view){
        TextView tv;
        switch(view.getId()){
            case R.id.AutoSwitchMinusButton:
                tv = (TextView) findViewById(R.id.AutoSwitchTextLabel);
                if(Integer.valueOf(tv.getText().toString())>0)
                    tv.setText(Integer.valueOf(tv.getText().toString())-1+"");
                break;
            case R.id.AutoScaleMinusButton:
                tv = (TextView) findViewById(R.id.AutoScaleTextLabel);
                if(Integer.valueOf(tv.getText().toString())>0)
                    tv.setText(Integer.valueOf(tv.getText().toString())-1+"");
                break;
            case R.id.ExchangeZoneMinusButton:
                tv = (TextView) findViewById(R.id.ExchangeZoneTextLabel);
                if(Integer.valueOf(tv.getText().toString())>0)
                    tv.setText(Integer.valueOf(tv.getText().toString())-1+"");
                break;
            case R.id.OSwitchMinusButton:
                tv = (TextView) findViewById(R.id.OSwitchTextLabel);
                if(Integer.valueOf(tv.getText().toString())>0)
                    tv.setText(Integer.valueOf(tv.getText().toString())-1+"");
                break;
            case R.id.OpptSwitchMinusButton:
                tv = (TextView) findViewById(R.id.OpptSwitchTextLabel);
                if(Integer.valueOf(tv.getText().toString())>0)
                    tv.setText(Integer.valueOf(tv.getText().toString())-1+"");
                break;
            case R.id.ScaleMinusButton:
                tv = (TextView) findViewById(R.id.ScaleTextLabel);
                if(Integer.valueOf(tv.getText().toString())>0)
                    tv.setText(Integer.valueOf(tv.getText().toString())-1+"");
                break;
        }
    }

    public void add(View view){
        TextView tv;
        switch (view.getId()){
            case R.id.AutoSwitchPlusButton:
                tv = (TextView) findViewById(R.id.AutoSwitchTextLabel);
                tv.setText(Integer.valueOf(tv.getText().toString())+1+"");
                break;
            case R.id.AutoScalePlusButton:
                tv = (TextView) findViewById(R.id.AutoScaleTextLabel);
                tv.setText(Integer.valueOf(tv.getText().toString())+1+"");
                break;
            case R.id.ExchangeZonePlusButton:
                tv = (TextView) findViewById(R.id.ExchangeZoneTextLabel);
                tv.setText(Integer.valueOf(tv.getText().toString())+1+"");
                break;
            case R.id.OSwitchPlusButton:
                tv = (TextView) findViewById(R.id.OSwitchTextLabel);
                tv.setText(Integer.valueOf(tv.getText().toString())+1+"");
                break;
            case R.id.OpptSwitchPlusButton:
                tv = (TextView) findViewById(R.id.OpptSwitchTextLabel);
                tv.setText(Integer.valueOf(tv.getText().toString())+1+"");
                break;
            case R.id.ScalePlusButton:
                tv = (TextView) findViewById(R.id.ScaleTextLabel);
                tv.setText(Integer.valueOf(tv.getText().toString())+1+"");
                break;
        }
    }

    public void done(View view){
        String shortOutput = "";
        String fullOutput = "";

        //Cross Auto Line
        String crossAuto = "";
        CheckBox autoLine = (CheckBox) findViewById(R.id.autoLine);
        crossAuto += checkBox(autoLine, crossAuto);
        shortOutput += crossAuto;
        fullOutput += crossAuto;

        //Cubes scored in Switch in Auto
        String autoSwitch = ((TextView) findViewById(R.id.AutoSwitchTextLabel)).getText().toString();
        shortOutput += autoSwitch;
        fullOutput += autoSwitch;

        //Cubes scored in Scale in Auto
        String autoScale = ((TextView) findViewById(R.id.AutoScaleTextLabel)).getText().toString();
        shortOutput += autoScale;
        fullOutput += autoScale;

        //Cubes placed in Exchange in TeleOp
        String teleOpExchange = ((TextView) findViewById(R.id.ExchangeZoneTextLabel)).getText().toString();
        shortOutput += teleOpExchange;
        fullOutput += teleOpExchange;

        //Cubes scored in own Switch in TeleOp
        String teleOpOwnSwitch = ((TextView) findViewById(R.id.OSwitchTextLabel)).getText().toString();
        shortOutput += teleOpOwnSwitch;
        fullOutput += teleOpOwnSwitch;

        //Cubes scored in opponent Switch in TeleOp
        String teleOpOpptSwitch = ((TextView) findViewById(R.id.OpptSwitchTextLabel)).getText().toString();
        shortOutput += teleOpOpptSwitch;
        fullOutput += teleOpOpptSwitch;

        //Cubes scored in Scale in TeleOp
        String teleOpScale = ((TextView) findViewById(R.id.ScaleTextLabel)).getText().toString();
        shortOutput += teleOpScale;
        fullOutput += teleOpScale;


    }

    /*
    Do not change, stays the same every year
    Do not delete even if you do not end up using ir
    Parameter: id tag of the radio button
    Returns label of radio button
    How to use:
    RadioGroup floorFuelCollect = (RadioGroup) findViewById(R.id.floorFuelCollect);
    int floorFuelCollectID = floorFuelCollect.getCheckedRadioButtonId();
    */
    public String radioButton(int selectedRadioButtonID){
        if (selectedRadioButtonID != -1){
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
            String selectedRadioButtonText = selectedRadioButton.getText().toString(); //Radio button checked
            return selectedRadioButtonText;
        } else {
            return "";
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