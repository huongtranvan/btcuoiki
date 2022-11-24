package com.example.thutonghop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangChu extends AppCompatActivity {
    Button btnfrofile;
    Button btndanhsach1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        btnfrofile=(Button) findViewById(R.id.buttonprofile);
        btnfrofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mhfrofile1= new Intent(TrangChu.this,TrangProfile.class);
                startActivity(mhfrofile1);
            }
        });
        btndanhsach1=(Button) findViewById(R.id.buttondanhsach);
        btndanhsach1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mhdanhsach= new Intent(TrangChu.this,TrangDanhSach.class);
                startActivity(mhdanhsach);
            }
        });

    }
}
