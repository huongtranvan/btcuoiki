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

public class TrangChinhSua extends AppCompatActivity {
     Button btnTrangChu;
     Button btnupdate;
     EditText LinhAnh1, MotaMayTinh1,GiaMayTinh1,TenMayTinh1;
    public static boolean status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chinh_sua);
        //anh xa view
        GiaMayTinh1= findViewById(R.id.giamay);
        LinhAnh1 = findViewById(R.id.linhanh);
        MotaMayTinh1= findViewById(R.id.motamay);
        TenMayTinh1= findViewById(R.id.tenmay);

        btnupdate = findViewById(R.id.btn_update);

        // luu
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 inserData();
                clearAll();
            }
        });
        btnTrangChu=(Button) findViewById(R.id.btnBack1);
        btnTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mhTrangChu=new Intent(TrangChinhSua.this,TrangChu.class);
                startActivity(mhTrangChu);
            }
        });
    }
    private void inserData(){

        Map<String,Object> map = new HashMap<>();
        map.put("name",TenMayTinh1.getText().toString());
        map.put("gia",GiaMayTinh1.getText().toString());
        map.put("mota",MotaMayTinh1.getText().toString());
        map.put("img",LinhAnh1.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("ORDERMAYTINH").push()
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(TrangChinhSua.this,"Thêm móm ăn thành công!",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TrangChinhSua.this,"Thêm món ăn thất bại!",Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private  void clearAll(){
        GiaMayTinh1.setText("");
        TenMayTinh1.setText("");
        MotaMayTinh1.setText("");
        LinhAnh1.setText("");
    }
}

