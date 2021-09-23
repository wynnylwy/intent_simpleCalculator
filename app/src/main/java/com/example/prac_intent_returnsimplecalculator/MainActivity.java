package com.example.prac_intent_returnsimplecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText number1;
    EditText number2;
    TextView textViewHistory;
    RadioGroup radioGroupButton;
    RadioButton radioButton;
    Button btnCalculate;
    String operator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        textViewHistory = findViewById(R.id.txtHistoryAnswer);
        radioGroupButton = findViewById(R.id.radioBtnGroup);
        btnCalculate = findViewById(R.id.btnCalculate);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Welcome To Simple Calculator - 1");

        radioGroupButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioBtnID = radioGroupButton.getCheckedRadioButtonId();
                radioButton = findViewById(radioBtnID);
                boolean checked = radioButton.isChecked();

                switch (radioButton.getId()) {
                    case R.id.btnPlus:
                        if (checked) {
                            operator = "+";
                        }
                        break;
                    case R.id.btnSubtract:
                        if (checked) {
                            operator = "-";
                        }
                        break;
                    case R.id.btnMultiply:
                        if (checked) {
                            operator = "*";
                        }
                        break;
                    case R.id.btnDivide:
                        if (checked) {
                            operator = "/";
                        }
                        break;
                    default:
                        operator = "";
                        break;
                }

            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int num1 = Integer.parseInt(number1.getText().toString());
                int num2 = Integer.parseInt(number2.getText().toString());

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("NUMBER1", num1);
                intent.putExtra("NUMBER2", num2);
                intent.putExtra("OPERATOR", operator);
                startActivityForResult(intent, 1);
                //requestCode = knowing which activity will come back from
                //ex: 1 = SecondActivity
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int result = data.getIntExtra("ANSWER", 0);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                textViewHistory.setText("History answer : " + result);
            } else {
                textViewHistory.setText("No result");
            }
        }
    }
}
