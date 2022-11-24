package com.example.thutonghop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangProfile extends AppCompatActivity {
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_profile);
      btnLogout=(Button) findViewById(R.id.ButtonLogout);
      btnLogout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent mhTrangProfile=new Intent(TrangProfile.this,TrangChinhSua.class);
              startActivity(mhTrangProfile);
          }
      });
    }
}