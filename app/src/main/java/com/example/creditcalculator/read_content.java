package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.creditcalculator.R.id.MyTable;
import static com.example.creditcalculator.R.id.tv_total;

public class read_content extends AppCompatActivity {
    private final String File_Subject="subject.xml";
    private final String File_Credit="credit.xml";
    private final String File_Credit_Need="credit_need.xml";
    private TextView tv_subject_show;
    private TextView tv_credit_show;
    private String word_subject;
    private String word_credit;
    private String word_credit_need;
    private TextView tv_total_show;
    private ConstraintLayout.LayoutParams my_table;
    private TextView tv_credit_need_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_content);
        tv_subject_show=this.findViewById(R.id.tv_subject);
        tv_credit_show=this.findViewById(R.id.tv_credit);
        tv_total_show=this.findViewById(R.id.tv_total);
        tv_credit_need_show=this.findViewById(R.id.tv_credit_need);
        word_subject=read(File_Subject);
        word_credit=read(File_Credit);
        word_credit_need=read(File_Credit_Need);
        tv_subject_show.setText(word_subject);
        tv_credit_show.setText(word_credit);
        tv_total_show.setText(sum(word_credit));
        tv_credit_need_show.setText(minus(word_credit_need,sum(word_credit)));
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
    private String sum(String num)
    {
        String[] num_temp=num.split("\n");
        int temp=0,total=0;
        for(int i=0;i<num_temp.length;i++)
        {
            try {
                temp=Integer.parseInt(num_temp[i]);
            }catch (NumberFormatException e) {
                temp = 0;
            }
            total+=temp;
        }
        return String.valueOf(total);
    }
    private String minus(String num1,String num2)
    {
        int temp1,temp2,total;
            try {
                temp1=Integer.parseInt(num1);
                temp2=Integer.parseInt(num2);
            }catch (NumberFormatException e) {
                temp1 = 0;
                temp2 = 0;
            }
            total=temp1-temp2;
        return String.valueOf(total);
    }
}