package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class add_content extends AppCompatActivity {
    private final String File_Subject = "subject.xml";
    private final String File_Credit = "credit.xml";
    private Button btn_send_show;
    private Button btn_back_show;
    private AutoCompleteTextView et_credit_show;
    private AutoCompleteTextView et_subject_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        et_subject_show = this.findViewById(R.id.et_subject);
        et_credit_show = this.findViewById(R.id.et_credit);
        btn_send_show = this.findViewById(R.id.btn_send);
        btn_back_show = this.findViewById(R.id.btn_back);
        final String[] content_subject_array={"普通物理學（二）","微積分（二）","線性代數","程式設計（二）",
                "數位電路導論","離散數學","計算機組織","演算法","JAVA軟體開發","基於遊戲的機器學習入門",
                "資訊專題（二）","編譯系統","Linux核心設計","即時系統導論","競技程式設計","多媒體系統與應用"
                ,"基因體資訊學","繪圖技術設計與應用","資料分析與學習基石","分散式系統與巨量資料平台",
                "軟體工程","普通物理學（一)","微積分（一）","程式設計（一）","工程數學","機率統計"
                ,"數位系統導論","作業系統","微算機原理與應用","編譯系統","計算理論","基礎國文（一）",
                "基礎國文（二）","體育（一）","體育（二）","體育（三）","體育（四）"
                ,"資訊專題（一）","服務學習（一）","服務學習（二）","服務學習（三）","數位系統實驗",
                "資訊安全", "自由軟體發開與社群發展", "基於物聯網之實境應用設計", "製造資訊與系統概論",
                "作業研究", "網頁應用及程式設計", "多處理機平行程式設計", "智慧型醫學資訊系統實作",
                "知識挖掘與資料工程導論", "模糊邏輯"
        };
        String[] content_credit_array={"0","1","2","3","4","5"};

        et_subject_show.setAdapter(new ArrayAdapter<String>(this ,android.R.layout.simple_dropdown_item_1line ,content_subject_array ));
        et_subject_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_subject_show.showDropDown();
            }
        });
        et_credit_show.setAdapter(new ArrayAdapter<String>(this ,android.R.layout.simple_dropdown_item_1line ,content_credit_array ));
        et_credit_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_credit_show.showDropDown();
            }
        });
        btn_send_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_credit_show.getText().toString().isEmpty() && !et_subject_show.getText().toString().isEmpty()
                        && isNumeric(et_credit_show.getText().toString())) {
                    save(et_subject_show.getText().toString().replace("\n","")+ "\n", File_Subject);
                    save(et_credit_show.getText().toString() + "\n", File_Credit);
                    et_credit_show.setText("");
                    et_subject_show.setText("");
                }
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
    private void save(String content, String filename) {
        FileOutputStream fileOutputStream=null;

        try {
            fileOutputStream = openFileOutput(filename,MODE_APPEND);
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
    public static boolean isNumeric(String str) {
        if (str != null && !"".equals(str.trim())) {
            return str.matches("^[0-9]*$");
        }
        return false;
    }
}