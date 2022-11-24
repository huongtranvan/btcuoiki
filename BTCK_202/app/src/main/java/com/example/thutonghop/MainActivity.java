package com.example.thutonghop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements   View.OnClickListener {
    private EditText edt_email_in, edt_pass_in;
    private Button btn_signin_in;
    private TextView layout_signUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        anhxa();
        layout_signUp.setOnClickListener(this);
        btn_signin_in.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dangki:
                startActivity(new Intent(this, TrangDangki.class));
                break;
            case R.id.button_dangnhap:
                userSignIn();
                break;
        }
    }

    private void userSignIn() {
        String Email = edt_email_in.getText().toString().trim();
        String MatKhau = edt_pass_in.getText().toString().trim();
        if (Email.isEmpty()) {
            edt_email_in.setError("Nhập Email");
            edt_email_in.requestFocus();
            return;

        }  if (MatKhau.isEmpty()) {
            edt_email_in.setError("Nhập Password");
            edt_email_in.requestFocus();

        }
        mAuth.signInWithEmailAndPassword(Email, MatKhau)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity.this, TrangChu.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Đăng nhập không thành công, vui lòng đăng nhập lại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void anhxa() {
        btn_signin_in = findViewById(R.id.button_dangnhap);
        edt_email_in = findViewById(R.id.editTextTextPersonName2);
        edt_pass_in = findViewById(R.id.IputPassword);
        layout_signUp = findViewById(R.id.dangki);
    }
}