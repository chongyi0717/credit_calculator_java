package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class delete_content extends AppCompatActivity {
    private String File_Subject = "subject.xml";
    private String File_Credit = "credit.xml";
    private Button btn_remove_show;
    private Button btn_back_show;
    private EditText et_credit_show;
    private EditText et_subject_show;
    private String word_subject;
    private String word_credit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_content);
        et_subject_show = this.findViewById(R.id.et_subject);
        et_credit_show = this.findViewById(R.id.et_credit);
        btn_remove_show = this.findViewById(R.id.btn_remove);
        btn_back_show = this.findViewById(R.id.btn_back);

        btn_remove_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(et_subject_show.getText().toString(),File_Subject);
                delete(et_credit_show.getText().toString(),File_Credit);
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

    private void delete(String word,String filename)
    {
        String content=read(filename);
        content=content.replace(word,"");
        save(content,filename);
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
    private String read(String filename)
    {
        FileInputStream fileInputStream=null;

        try {
            fileInputStream = openFileInput(filename);
            byte temp[] = new byte[1024];
            StringBuilder sb = new StringBuilder("");
            int len = 0;

            while ((len = fileInputStream.read(temp)) > 0){
                sb.append(new String(temp, 0, len));

            }

            String buffer=sb.toString();
            buffer=buffer.replaceAll("\\n","\n");
            return buffer;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
