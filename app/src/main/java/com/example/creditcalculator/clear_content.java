package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;
import java.io.IOException;

public class clear_content extends AppCompatActivity {
    private Button btn_yes_show;
    private Button btn_no_show;
    private String file_subject="subject.xml";
    private String file_credit="credit.xml";
    private String file_credit_need="credit_need.xml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_content);
        btn_yes_show=this.findViewById(R.id.btn_yes);
        btn_no_show=this.findViewById(R.id.btn_no);
        btn_yes_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_private("",file_subject);
                save_private("",file_credit);
                save_private("",file_credit_need);
                finish();
            }
        });
        btn_no_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void save_private(String content,String filename) {
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