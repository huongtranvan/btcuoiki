package com.example.thutonghop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Maps;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class TrangDangki extends  AppCompatActivity implements View.OnClickListener {
    private TextView dangki,dangki1;
    private EditText ten, tuoi, email, matkhau;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_dangki);
        mAuth = FirebaseAuth.getInstance();
        dangki=(Button)findViewById(R.id.button_Dangki) ;
        dangki.setOnClickListener(this);
        dangki1=(Button)findViewById(R.id.button_Huybo) ;
        dangki1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mhdangki=new Intent(TrangDangki.this,TrangChu.class);
                startActivity(mhdangki);
            }
        });
         ten=(EditText)  findViewById(R.id.text_name);
        tuoi=(EditText)  findViewById(R.id.text_NgaySinh);
        email=(EditText)  findViewById(R.id.Password2);
        matkhau=(EditText)  findViewById(R.id.Pasword1);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);

    }

        @Override
        public void onClick(View view) {
            DangkiNguoidung();
        }

    private void DangkiNguoidung() {
        String Ten= ten.getText().toString().trim();
        String MatKhau= matkhau.getText().toString().trim();
        String Email= email.getText().toString().trim();
        String Tuoi= tuoi.getText().toString().trim();
        if(Ten.isEmpty()){
            ten.setError("Nhập Tên");
            ten.requestFocus();

            return;
        }
        if (Email.isEmpty()){
            email.setError("Nhập Email");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Email khong hop le!");
            email.requestFocus();
            return;
        }
         if (Tuoi.isEmpty()){
           tuoi.setError("Nhập Tuổi");
            tuoi.requestFocus();
            return;
        }
       if(MatKhau.isEmpty()){
           matkhau.setError("Nhap mat khau");
           matkhau.requestFocus();
           return;
       }
       if(MatKhau.length()<6){
           matkhau.setError("Mat khau phai lon hon 5 ki tu!");
           matkhau.requestFocus() ;
           return ;
        }
       progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email,MatKhau)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(Ten, Tuoi,Email);
                           FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(TrangDangki.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.VISIBLE);

                                            }else {
                                                Toast.makeText(TrangDangki.this,"Đăng ký không thành công, Đăng ký lại!",Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }

                                        }
                                    });

                        }else {
                            Toast.makeText(TrangDangki.this,"Đăng ký không thành công, Đăng ký lại!",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    }


