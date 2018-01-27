package com.team20.matchscouting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ben on 1/7/2018.
 */

public class ScoutScreen extends Activity{
    //Expandable List
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHolder = new ArrayList<>();
    private HashMap<String,List<String>> listHash = new HashMap<>();;
    private List<String> park = new ArrayList<>();
    private List<String> climb = new ArrayList<>();

    //Data points
    private static Data data = new Data();
    private String firstName = "";
    private String lastName = "";
    private String alliance = "";
    private String match = "";
    private String teamNumber = "";
    private String replay = "";
    private String parkChosen = "";
    private String climbChosen = "";

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.scout_screen);

        //Info from previous screen
        firstName = getIntent().getStringExtra("First_Name");
        lastName = getIntent().getStringExtra("Last_Name");
        match = getIntent().getStringExtra("Match_Number");
        alliance =  getIntent().getStringExtra("Alliance_Color");
        teamNumber = getIntent().getStringExtra("Team_Number");
        replay = getIntent().getStringExtra("Replay_Match");

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHolder,listHash);
        listView.setAdapter(listAdapter);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String name = "";
                if (groupPosition == 0){
                    String parkChild = park.get(childPosition); //Park chosen
                    parkChosen = parkChild;
                    name = "Park: " + parkChild;
                } else if (groupPosition == 1) {
                    String climbChild = climb.get(childPosition); //Climb chosen
                    climbChosen = climbChild;
                    name = "Climb: " + climbChild;
                }
                listDataHolder.set(groupPosition, name);
                reassign();
                Toast.makeText(getApplicationContext(), listDataHolder.get(groupPosition), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void initData(){
        listDataHolder.add("Park");
        park.add("Successful");
        park.add("Failed");
        park.add("Did not Attempt/Climbed");

        listDataHolder.add("Climb");
        climb.add("Successful (was helped)");
        climb.add("Successful (was NOT helped)");
        climb.add("Failed (was helped)");
        climb.add("Failed (was NOT helped)");
        climb.add("Did not Attempt");
        reassign();
    }

    private void reassign(){
        listHash.put(listDataHolder.get(0),park);
        listHash.put(listDataHolder.get(1),climb);
    }

    private void setListViewHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null, listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10) {
            height = 200;
        }
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
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

        shortOutput += match + replay + "\t" + alliance + "\t" + teamNumber;

        //Starting Position
        RadioGroup startPos = (RadioGroup) findViewById(R.id.startPosition);
        int startPosID = startPos.getCheckedRadioButtonId();
        String startPosition = radioButton(startPosID);
        if (!startPosition.equals("")) {
            shortOutput += "\t" + startPosition;

            //Cross Auto Line
            String crossAuto = "";
            CheckBox autoLine = (CheckBox) findViewById(R.id.autoLine);
            crossAuto += checkBox(autoLine, crossAuto);
            shortOutput += "\t" + "crossed";

            //Cubes scored in Switch in Auto
            String autoSwitch = ((TextView) findViewById(R.id.AutoSwitchTextLabel)).getText().toString();
            shortOutput += "\t" + autoSwitch;

            //Cubes scored in Scale in Auto
            String autoScale = ((TextView) findViewById(R.id.AutoScaleTextLabel)).getText().toString();
            shortOutput += "\t" + autoScale;

            //Cubes placed on wrong side of switch/scale
            String wrongSide = "";
            CheckBox wrongSideBox = (CheckBox) findViewById(R.id.wrongSide);
            wrongSide += checkBox(wrongSideBox, wrongSide);
            shortOutput += "\t" + "wrong";

            //Cubes placed in Exchange in TeleOp
            String teleOpExchange = ((TextView) findViewById(R.id.ExchangeZoneTextLabel)).getText().toString();
            shortOutput += "\t" + teleOpExchange;

            //Cubes scored in own Switch in TeleOp
            String teleOpOwnSwitch = ((TextView) findViewById(R.id.OSwitchTextLabel)).getText().toString();
            shortOutput += "\t" + teleOpOwnSwitch;

            //Cubes scored in opponent Switch in TeleOp
            String teleOpOpptSwitch = ((TextView) findViewById(R.id.OpptSwitchTextLabel)).getText().toString();
            shortOutput += "\t" + teleOpOpptSwitch;

            //Cubes scored in Scale in TeleOp
            String teleOpScale = ((TextView) findViewById(R.id.ScaleTextLabel)).getText().toString();
            shortOutput += "\t" + teleOpScale;

            //Parking
            if (!parkChosen.equals("")) {
                shortOutput += "\t" + parkChosen;

                //Climbing
                if (!climbChosen.equals("")) {
                    shortOutput += "\t" + climbChosen;

                    EditText editSuccess = (EditText) findViewById(R.id.editSuccessfulAid);
                    String successAid = editSuccess.getText().toString();
                    if (!successAid.equals("")) {
                        int successNum = Integer.parseInt(successAid);
                        if (successNum < 3) {
                            shortOutput += "\t" + successNum;

                            EditText editFail = (EditText) findViewById(R.id.editFailedAid);
                            String failAid = editFail.getText().toString();
                            if (!failAid.equals("")) {
                                int failNum = Integer.parseInt(failAid);
                                if (failNum < 3) {
                                    shortOutput += "\t" + failNum;

                                    if (successNum+failNum>3) { //Cant have more than 2 fail and success aided climb together

                                        EditText editComments = (EditText) findViewById(R.id.commentText);
                                        String comments = editComments.getText().toString();
                                        if (comments.equals("")) {
                                            shortOutput += "\t" + comments;
                                        } else {
                                            shortOutput += "\tN/A";
                                        }

                                        fullOutput += firstName + "\t" + lastName + "\t" + shortOutput;
                                        data.save(shortOutput, fullOutput);
                                        finish();
                                    } else {
                                        System.err.println("Number of failed and successful aided climb attempts together is too high");
                                        Toast.makeText(getApplicationContext(), "Number of failed and successful aided climb attempts together is too high", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    System.err.println("Number of failed aided climb attempts is too high");
                                    Toast.makeText(getApplicationContext(), "Number of failed aided climb attempts is too high", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                System.err.println("Failed aided climb attempts is empty");
                                Toast.makeText(getApplicationContext(), "Failed aided climb attempts is empty", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            System.err.println("Number of successful aided climb attempts is too high");
                            Toast.makeText(getApplicationContext(), "Number of successful aided climb attempts is too high", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        System.err.println("Successful aided climb attempts is empty");
                        Toast.makeText(getApplicationContext(), "Successful aided climb attempts is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    System.err.println("Climbing is not selected");
                    Toast.makeText(getApplicationContext(), "Climbing is not selected", Toast.LENGTH_SHORT).show();
                }
            } else {
                System.err.println("Parking is not selected");
                Toast.makeText(getApplicationContext(), "Parking is not selected", Toast.LENGTH_SHORT).show();
            }
        } else {
            System.err.println("Starting Position is not selected");
            Toast.makeText(getApplicationContext(), "Starting Position is not selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { //Back Arrow pressed
            String choice = data.getStringExtra("Choice");
            if (choice.equals("leave")){
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivityForResult(new Intent(ScoutScreen.this,ExitPrompt.class), 6);
    }
    /*
    Do not change, stays the same every year
    Do not delete even if you do not end up using ir
    Parameter: id tag of the radio button
    Returns label of radio button
    How to use:
    RadioGroup floorFuelCollect = (RadioGroup) findViewById(R.id.floorFuelCollect);
    int floorFuelCollectID = floorFuelCollect.getCheckedRadioButtonId();
    String floorFuelAbility = radioButton(floorFuelCollectID);
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