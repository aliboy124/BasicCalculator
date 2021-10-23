package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button eq = (Button) findViewById(R.id.equal);
        eq.setEnabled(false);
    }

    public void modeSwitch(View v){
        Switch mySwitch = (Switch) findViewById(R.id.switch1);
        if(mySwitch.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    protected String num1="";
    protected String num2="";
    protected String operator= "";

    public void onExit(View v){
        this.finishAndRemoveTask();
    }

    public void enterDigit(View v){
        EditText inp = (EditText) findViewById(R.id.input);
        Button btn = (Button) findViewById(v.getId());
        inp.append(btn.getText().toString());
        if(operator!="") {
            Button eq = (Button) findViewById(R.id.equal);
            eq.setEnabled(true);
        }else{num1 = inp.getText().toString();}
    }

    public void enterOperation(View v){
        if(num1!="" && operator=="") {
            EditText inp = (EditText) findViewById(R.id.input);
            EditText prev = (EditText) findViewById(R.id.prevExpression);
            num1 = inp.getText().toString();
            inp.setText(String.valueOf(""));
            Button btn = (Button) findViewById(v.getId());
            switch (v.getId()) {
                case R.id.power:
                    prev.append(num1);
                    prev.append(btn.getText().toString());
                    operator = "^";
                    break;
                case R.id.percentage:
                    prev.append(num1);
                    prev.append(btn.getText().toString());
                    operator = "%";
                    break;
                case R.id.divide:
                    prev.append(num1);
                    prev.append(btn.getText().toString());
                    operator = "/";
                    break;
                case R.id.multiply:
                    prev.append(num1);
                    prev.append(btn.getText().toString());
                    operator = "*";
                    break;
                case R.id.add:
                    prev.append(num1);
                    prev.append(btn.getText().toString());
                    operator = "+";
                    break;
                case R.id.subtract:
                    prev.append(num1);
                    prev.append(btn.getText().toString());
                    operator = "-";
                    break;
                default:
                    break;
            }
        }else{}
    }

    public void evaluate(View v){
        if(num1!="") {
            EditText inp = (EditText) findViewById(R.id.input);
            EditText prev = (EditText) findViewById(R.id.prevExpression);
            num2 = inp.getText().toString();
            inp.setText(String.valueOf(""));
            double result = 0;
            switch (operator) {
                case "^":
                    prev.append(num2);
                    result = Math.pow(Double.valueOf(num1),Double.valueOf(num2));
                    break;
                case "%":
                    prev.append(num2);
                    result = (Double.valueOf(num1)/Double.valueOf(num2))*100;
                    break;
                case "/":
                    prev.append(num2);
                    result = (Double.valueOf(num1)/Double.valueOf(num2));
                    operator = "/";
                    break;
                case "*":
                    prev.append(num2);
                    result = (Double.valueOf(num1)*Double.valueOf(num2));
                    operator = "*";
                    break;
                case "+":
                    prev.append(num2);
                    result = (Double.valueOf(num1)+Double.valueOf(num2));
                    operator = "+";
                    break;
                case "-":
                    prev.append(num2);
                    result = (Double.valueOf(num1)-Double.valueOf(num2));
                    operator = "-";
                    break;
                default:
                    break;
            }
            inp.setText(String.valueOf(result));
            Button eq = (Button) findViewById(R.id.equal);
            eq.setEnabled(false);
            operator = "";
            num1="";
            num2="";
        }else{}
    }

    public void clear(View v){
        operator = "";
        num1="";
        num2="";
        EditText inp = (EditText) findViewById(R.id.input);
        EditText prev = (EditText) findViewById(R.id.prevExpression);
        inp.setText("");
        prev.setText("");
    }

}