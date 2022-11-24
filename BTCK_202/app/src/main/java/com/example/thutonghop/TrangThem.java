package com.example.thutonghop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class TrangThem extends AppCompatActivity {
        Button btnsave,btnback;
        EditText LinhAnh, MotaMayTinh,GiaMayTinh,TenMayTinh;
        public static boolean status = false;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_trang_them);
            //anh xa view
            GiaMayTinh= findViewById(R.id.giamay);
            LinhAnh = findViewById(R.id.linhanh);
            MotaMayTinh= findViewById(R.id.motamay);
            TenMayTinh= findViewById(R.id.tenmay);

            btnback = findViewById(R.id.btnBack);
            btnsave= findViewById(R.id.btnSave);

            // luu
           btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    inserData();
                    clearAll();
                }
            });

            //thoat
            btnback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TrangThem.this,MainActivity.class);
                    status = true;
                    startActivity(intent);

                }
            });
        }

        private void inserData(){

            Map<String,Object> map = new HashMap<>();
            map.put("name",TenMayTinh.getText().toString());
            map.put("gia",GiaMayTinh.getText().toString());
            map.put("mota",MotaMayTinh.getText().toString());
            map.put("img",LinhAnh.getText().toString());

            FirebaseDatabase.getInstance().getReference().child("ORDERMAYTINH").push()
                    .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TrangThem.this,"Thêm máy thành công!",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TrangThem.this,"Thêm máy thất bại!",Toast.LENGTH_SHORT).show();

                        }
                    });
        }

        private  void clearAll(){
            GiaMayTinh.setText("");
            TenMayTinh.setText("");
            MotaMayTinh.setText("");
            LinhAnh.setText("");
        }
    }