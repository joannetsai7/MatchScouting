package com.team20.matchscouting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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

    public void Subtract(View view){
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

    public void Add(View view){
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
}