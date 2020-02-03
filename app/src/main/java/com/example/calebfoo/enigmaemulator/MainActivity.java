package com.example.calebfoo.enigmaemulator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    int prevlength = 0;
    boolean firstTry1=true;
    boolean firstTry2=true;
    boolean firstTry3=true;

    List<String> listRotorname = new ArrayList<>();
    List<String> listReflector = new ArrayList<>();
    Reflector reflector;
    StringBuilder input = new StringBuilder();
    StringBuilder output = new StringBuilder();
    Rotors rotorsClass = new Rotors();
    Steckerbrett plugboard = new Steckerbrett();
    LinearLayout linear;
    Spinner reflectorSpin;
    Spinner[] wheels = new Spinner[3];
    EditText plugInput;
    Button insertPlug;
    Button clearPlug;
    Button resetBtn;
    EditText inputText;
    TextView inputBox;
    TextView outputBox;
    Stack<TextView> stackTv = new Stack<>();
    char[] rotors_pos = new char[3];
    EditText[] wheelPos = new EditText[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear = (LinearLayout)findViewById(R.id.linearLayout);
        reflectorSpin = (Spinner)findViewById(R.id.reflectorChoice);
        wheels[0] = (Spinner)findViewById(R.id.wheel1);
        wheels[1] = (Spinner)findViewById(R.id.wheel2);
        wheels[2] = (Spinner)findViewById(R.id.wheel3);
        wheelPos[0] = (EditText)findViewById(R.id.wheelPos1);
        wheelPos[1] = (EditText)findViewById(R.id.wheelPos2);
        wheelPos[2] = (EditText)findViewById(R.id.wheelPos3);
        plugInput = (EditText)findViewById(R.id.textBoxPlug);
        insertPlug  = (Button)findViewById(R.id.btnEnter);
        clearPlug = (Button)findViewById(R.id.btnX);
        //resetBtn = (Button)findViewById(R.id.btnReset);
        inputText = (EditText)findViewById(R.id.actInput);
        inputBox = (TextView)findViewById(R.id.inputText);
        outputBox = (TextView)findViewById(R.id.outputText);
        wheelPos[0].setVisibility(View.INVISIBLE);
        wheelPos[1].setVisibility(View.INVISIBLE);
        wheelPos[2].setVisibility(View.INVISIBLE);
        setupRotorList();
        final ArrayAdapter<String> rotorAdapter = new ArrayAdapter<>(this, R.layout.spinner_item,listRotorname);
        wheels[0].setAdapter(rotorAdapter);
        wheels[1].setAdapter(rotorAdapter);
        wheels[2].setAdapter(rotorAdapter);
        final ArrayAdapter<String> reflectAdapter = new ArrayAdapter<>(this, R.layout.spinner_item,listReflector);
        reflectorSpin.setAdapter(reflectAdapter);


        reflectorSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text  = parent.getItemAtPosition(position).toString();
                reflector = new Reflector(text);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        wheelPos[0].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (wheelPos[0].isFocused()) {
                    if (s.length() > 0) {
                        char c = s.charAt(0);
                        if (c >= 'a' && c <= 'z') {
                            c = Character.toUpperCase(c);
                            wheelPos[0].setText(String.valueOf(c));
                            rotorsClass.rotors[0].setInitialPos(c);
                        } else if (c >= 'A' && c <= 'Z') {
                            rotorsClass.rotors[0].setInitialPos(c);
                        } else {
                            Toast.makeText(getApplicationContext(), "Not a Character", Toast.LENGTH_SHORT).show();
                            wheelPos[0].setText("");
                        }
                        Log.i("WheelPOS2",String.valueOf(rotorsClass.rotors[2].getPos()));
                    }

                }

            }
        });
        wheelPos[1].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int bekypefore, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (wheelPos[1].isFocused()) {
                    if (s.length() > 0) {
                        char c = s.charAt(0);
                        if (c >= 'a' && c <= 'z') {
                            c = Character.toUpperCase(c);
                            wheelPos[1].setText(String.valueOf(c));
                            rotorsClass.rotors[1].setInitialPos(c);
                        } else if (c >= 'A' && c <= 'Z') {
                            rotorsClass.rotors[1].setInitialPos(c);
                        } else {
                            Toast.makeText(getApplicationContext(), "Not a Character", Toast.LENGTH_SHORT).show();
                            wheelPos[1].setText("");
                        }
                        Log.i("WheelPOS2",String.valueOf(rotorsClass.rotors[2].getPos()));
                    }

                }



            }
        });
        wheelPos[2].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (wheelPos[2].isFocused()){
                    if(s.length()>0){
                        char c =  s.charAt(0);
                        if(c >= 'a' && c <= 'z'){
                            c = Character.toUpperCase(c);
                            wheelPos[2].setText(String.valueOf(c));
                            rotorsClass.rotors[2].setInitialPos(c);
                        }else if(c >= 'A' && c <= 'Z'){
                            rotorsClass.rotors[2].setInitialPos(c);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Not a Character",Toast.LENGTH_SHORT).show();
                            wheelPos[2].setText("");
                        }
                        Log.i("WheelPOS2",String.valueOf(rotorsClass.rotors[2].getPos()));
                    }

                }
            }
        });

        wheels[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text  = parent.getItemAtPosition(position).toString();
                if(text!="---"){
                    Rotor newRotor = new Rotor(text, rotorsClass.getRotorString(text), rotorsClass.getKnockOffPoints(text));
                    rotorsClass.rotors[0] = newRotor;
                    wheelPos[0].setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        wheels[1].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text  = parent.getItemAtPosition(position).toString();
                if(!Objects.equals(text, "---")){
                    Rotor newRotor = new Rotor(text, rotorsClass.getRotorString(text), rotorsClass.getKnockOffPoints(text));
                    rotorsClass.rotors[1] = newRotor;
                    wheelPos[1].setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        wheels[2].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text  = parent.getItemAtPosition(position).toString();
                if(!Objects.equals(text, "---")){
                    Rotor newRotor = new Rotor(text, rotorsClass.getRotorString(text), rotorsClass.getKnockOffPoints(text));
                    rotorsClass.rotors[2] = newRotor;
                    wheelPos[2].setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        insertPlug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = plugInput.getText().toString();
                if (temp.contains(":")){
                    temp = temp.toUpperCase();
                    plugboard.cleanString(temp);
                    TextView newtv = createNewTextView(temp);
                    newtv.setTextColor(Color.WHITE);
                    stackTv.push(newtv);
                    linear.addView(newtv);
                    plugInput.setText("");
                }
                else{
                    plugInput.setText("");
                    Toast.makeText(getApplicationContext(),"Input typed in incorrect format",Toast.LENGTH_SHORT).show();
                }
            }
        });


        clearPlug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plugboard.removePlug();
                linear.removeView(stackTv.pop());
            }
        });

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>=prevlength) {
                    char c = s.charAt(s.length() - 1);
                    if (c >= 'a' && c <= 'z') {
                        c = Character.toUpperCase(c);
                    }
                    if (c >= 'A' && c <= 'Z') {
                        input.append(c);
                        inputBox.setText(input.toString());
                        rotorsClass.rotorStep();
                        for (int i = 2; i >= 0; i--) {
                            wheelPos[i].setText(String.valueOf((char) (rotorsClass.rotors[i].getPos() + 65)));
                        }
                        c = encryptLetter(c);
                        output.append(c);
                        outputBox.setText(output.toString());

                        prevlength = s.length();
                    }
                }
            }
        });

    }
    private char encryptLetter(char c){
        return plugboard.checkSwap(rotorsClass.sendForward(reflector.reflect(rotorsClass.sendInverse(c))));
    }


    private boolean checkRotors(Rotors r){
        for(Rotor ro:r.rotors){
            if(ro == null){
                return false;
            }
        }
        return true;
    }

    private void setupRotorList(){
        listRotorname.add("---");
        listRotorname.add("I");
        listRotorname.add("II");
        listRotorname.add("III");
        listRotorname.add("IV");
        listRotorname.add("V");
        listRotorname.add("VI");
        listRotorname.add("VII");
        listRotorname.add("VIII");
        listReflector.add("b_thin");
        listReflector.add("c_thin");

    }
    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(text);
        return textView;
    }

}
