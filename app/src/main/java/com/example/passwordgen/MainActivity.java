package com.example.passwordgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    int Total;
    int Length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView box_total = (AutoCompleteTextView) findViewById(R.id.box_total);
        AutoCompleteTextView box_length = (AutoCompleteTextView) findViewById(R.id.box_length);
        Button btnGenerate = (Button) findViewById(R.id.btnGenerate);
        Button btnReset =  (Button) findViewById(R.id.btnReset);
        EditText Resultbox = (EditText) findViewById(R.id.Resultbox);
        TextView P_Strength = (TextView) findViewById(R.id.P_Strength);


        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Total = Integer.parseInt(Objects.requireNonNull(box_total.getText()).toString());
                    Length = Integer.parseInt(Objects.requireNonNull(box_length.getText()).toString());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Empty field not allowed!",
                            Toast.LENGTH_SHORT).show();
                }

                CloseKeyboard();

                String[] randomPasswords = new String[Total];

                for (int i = 0; i < Total; i++) {
                    String randomPassword = "";

                    for (int l = 0; l < Length; l++) {
                        randomPassword += randomChar();

                    }
                    randomPasswords[i] = randomPassword;
                }
                PrintArray(randomPasswords);

                int InputLength = Integer.parseInt(box_length.getText().toString());

                if (InputLength >= 1 ){
                    P_Strength.setText("Weak");
                    P_Strength.setTextColor(Color.RED);
                }
                if (InputLength >= 8){
                    P_Strength.setText("Average");
                    P_Strength.setTextColor(Color.YELLOW);
                }
                if (InputLength >= 12 ){
                    P_Strength.setText("Strong");
                    P_Strength.setTextColor(Color.GREEN);
                }
            }

            public void PrintArray(String[] arr) {
                for (int i = 0; i < arr.length; i++) {
                    Resultbox.append(arr[i] + " \n");
                }
                Toast.makeText(MainActivity.this, "Generated Passwords", Toast.LENGTH_SHORT).show();
            }


        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resultbox.setText("");
                box_length.setText("");
                box_total.setText("");
                P_Strength.setText("");
                Toast.makeText(MainActivity.this, "Reset Completed!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public char randomChar() {
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);
        CheckBox checkBox4 = findViewById(R.id.checkBox4);

        int rand = (int) (Math.random() * 62);

        if (checkBox4.isChecked()) {
            if (rand <= 9) {
                int number = rand + 48;
                return (char) (number);
            }
        }

        if (checkBox3.isChecked()) {
            if (rand <= 35) {
                int uppercase = rand + 55;
                return (char) (uppercase);
            }

        }

        if (checkBox2.isChecked()) {
            if (rand >= 36) {
                int lowercase = rand + 61;
                return (char) (lowercase);
            }
        }
        return randomChar();
    }
    private void CloseKeyboard () {

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

