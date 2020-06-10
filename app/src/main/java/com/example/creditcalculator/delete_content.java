package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class delete_content extends AppCompatActivity {
    private String File_Subject = "subject.xml";
    private String File_Credit = "credit.xml";
    private Button btn_remove_show;
    private Button btn_back_show;
    private AutoCompleteTextView et_subject_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_content);
        et_subject_show = this.findViewById(R.id.et_subject);
        btn_remove_show = this.findViewById(R.id.btn_remove);
        btn_back_show = this.findViewById(R.id.btn_back);
        String content_subject=read(File_Subject);
        String[] content_subject_array=content_subject.split("\n");
        et_subject_show.setAdapter (new ArrayAdapter<String>(this ,android.R.layout.simple_dropdown_item_1line ,content_subject_array ));
        et_subject_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_subject_show.showDropDown();
            }
        });
        btn_remove_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(et_subject_show.getText().toString(), File_Subject,File_Credit);
                et_subject_show.setText("");
            }
        });
        btn_back_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_subject_show.setText("");
                finish();
            }
        });
    }

    private void delete(String word,String filename_subject,String filename_credit)
    {
        save_append("",File_Subject);
        save_append("",File_Credit);
        String content_subject=read(filename_subject);
        String content_credit=read(filename_credit);
        String[] content_subject_array=content_subject.split("\n");
        String[] content_credit_array=content_credit.split("\n");
        Map<String,String> map=new HashMap<>();
        for(int i=0;i<content_subject_array.length;i++)
        {
            map.put(content_subject_array[i],content_credit_array[i]);
        }
        map.remove(word);
        save_private("",filename_subject);
        save_private("",filename_credit);
        for(Map.Entry<String,String> vo:map.entrySet())
        {
            save_append(vo.getKey()+"\n",filename_subject);
            save_append(vo.getValue()+"\n",filename_credit);
        }
    }
    private void save_append(String content,String filename) {
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
