package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;

public class credit_need extends AppCompatActivity {
    private final String File_Credit_Need = "credit_need.xml";
    private Button btn_send_show;
    private EditText et1_show;
    private Button btn_quit_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_need);
        btn_send_show=this.findViewById(R.id.btn_send);
        et1_show=this.findViewById(R.id.et1);
        btn_quit_show=this.findViewById(R.id.btn_quit);
        btn_send_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(et1_show.getText().toString(),File_Credit_Need);
                et1_show.setText("");
                finish();
            }
        });

        btn_quit_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void save(String content,String filename) {
        FileOutputStream fileOutputStream=null;

        try {
            fileOutputStream = openFileOutput(filename, MODE_PRIVATE);
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