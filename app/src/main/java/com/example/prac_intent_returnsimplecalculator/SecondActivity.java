package com.example.prac_intent_returnsimplecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView textViewAnswer;
    Button btnCalculateAgain;
    int number1, number2;
    int callbackAnswer = 0;
    int answer = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewAnswer = findViewById(R.id.txtAnswer);
        btnCalculateAgain = findViewById(R.id.btnCalculateAgain);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Welcome To Simple Calculator - 2");

        //Get intent
        Intent intent = getIntent();
        number1 = intent.getIntExtra("NUMBER1", 0);
        //Log.i("final" , "Final: " + finalCallbackAnswer);

        number2 = intent.getIntExtra("NUMBER2", 0);
        //Log.i("final" , "Final: " + finalCallbackAnswer);

        String operator = intent.getStringExtra("OPERATOR");
        //Log.i("final" , "Final: " + finalCallbackAnswer);


        callbackAnswer = calculateAnswer(operator, answer);
        int finalCallbackAnswer = callbackAnswer;

        textViewAnswer.setText("Answer: " + callbackAnswer);

        btnCalculateAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Pass back intent
                Intent intentBack = new Intent();
                intentBack.putExtra("ANSWER", finalCallbackAnswer);
                setResult(RESULT_OK, intentBack);
                finish();
            }
        });
    }

    public int calculateAnswer(String operator, int answer) {
        switch (operator) {
            case "+":
                answer = number1 + number2;
                break;
            case "-":
                answer = number1 - number2;
                break;
            case "*":
                answer = number1 * number2;
                break;
            case "/":
                answer = number1 / number2;
                break;

            default:
                answer = 0;
                break;
        }
        return answer;
    }
}
