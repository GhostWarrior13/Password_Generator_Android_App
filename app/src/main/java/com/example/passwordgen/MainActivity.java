package com.example.passwordgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    int Total;
    int Length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView box_total = findViewById(R.id.box_total);
        AutoCompleteTextView box_length = findViewById(R.id.box_length);
        Button btnGenerate = findViewById(R.id.btnGenerate);
        Button btnReset = findViewById(R.id.btnReset);
        EditText Resultbox = findViewById(R.id.Resultbox);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Total =Integer.parseInt( Objects.requireNonNull(box_total.getText()).toString());
                    Length = Integer.parseInt( Objects.requireNonNull(box_length.getText()).toString());
                }catch (Exception e){
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
        }

            public void PrintArray(String[] arr){
                for (int i = 0; i < arr.length ;i++){
                    Resultbox.append(arr[i] + " \n");
                }Toast.makeText(MainActivity.this,"Generated Passwords",Toast.LENGTH_SHORT).show();
            }


    });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resultbox.setText("");
                box_length.setText("");
                box_total.setText("");
                Toast.makeText(MainActivity.this,"Reset Completed!",Toast.LENGTH_SHORT).show();
            }
        });


}
    public static char randomChar(){
        int rand = (int)(Math.random()* 62);

        if (rand <= 9){
            int number = rand + 48;
            return (char) (number);

        }else if(rand <= 35){
            int uppercase = rand + 55;
            return (char)(uppercase);

        }else {
            int lowercase = rand + 61;
            return (char)(lowercase);
        }
    }

    private void CloseKeyboard(){

        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
