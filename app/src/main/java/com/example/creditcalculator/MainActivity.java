package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_add_credit_need).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,credit_need.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_add_subject_credit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,add_content.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_read_subject_credit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,read_content.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_remove_subject_credit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,delete_content.class);
                startActivity(intent);
            }
        });
    }
}