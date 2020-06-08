package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class add_content extends AppCompatActivity {
    private final String File_Subject = "subject.xml";
    private final String File_Credit = "credit.xml";
    private Button btn_send_show;
    private Button btn_back_show;
    private EditText et_credit_show;
    private EditText et_subject_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        et_subject_show = this.findViewById(R.id.et_subject);
        et_credit_show = this.findViewById(R.id.et_credit);
        btn_send_show = this.findViewById(R.id.btn_send);
        btn_back_show = this.findViewById(R.id.btn_back);

        btn_send_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(et_subject_show.getText().toString()+"\n", File_Subject);
                save(et_credit_show.getText().toString()+"\n", File_Credit);
                et_credit_show.setText("");
                et_subject_show.setText("");
            }
        });
        btn_back_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_credit_show.setText("");
                et_subject_show.setText("");
                finish();
            }
        });
    }
    private void save(String content,String filename) {
        FileOutputStream fileOutputStream=null;

        try {
            fileOutputStream = openFileOutput(filename, MODE_APPEND);
            fileOutputStream.write(content.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(fileOutputStream!=null)
            {
                try{
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}