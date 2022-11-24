package com.example.thutonghop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.AlertDialog;

import java.util.ArrayList;

public class TrangDanhSach extends AppCompatActivity {

    ListView lvDienthoai;
    ArrayList<DienThoai> arrayDienthoai;
   DienThoaiAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_danh_sach);

        AnhXa();
        adapter =new DienThoaiAdapter(this, R.layout.dong_dien_thoai, arrayDienthoai);
        lvDienthoai.setAdapter(adapter);
        lvDienthoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    startActivity(new Intent(TrangDanhSach.this, com.example.thutonghop.Profile.class));
                }
            }
        });
        lvDienthoai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alertDialogBuilder =new AlertDialog.Builder(TrangDanhSach.this);
                alertDialogBuilder.setMessage("Bán có muốn xóa không !");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // xóa sp đang nhấn giữ
                        arrayDienthoai.remove(i);
                        //cập nhật lại listview
                        adapter.notifyDataSetChanged();

                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //không làm gì
                    }
                });
                alertDialogBuilder.show();

                return false;
            }
        });
    }
    private void AnhXa () {
        lvDienthoai = (ListView) findViewById(R.id.listviewDienThoai);
        arrayDienthoai = new ArrayList<>();
        arrayDienthoai.add(new DienThoai("IPHONE 8", "Thiết kế và màn hình", R.drawable.iphone11));
        arrayDienthoai.add(new DienThoai("IPHONE 9", "Thiết kế và màn hình", R.drawable.iphone9));
        arrayDienthoai.add(new DienThoai("IPHONE 11", "Thiết kế và màn hình", R.drawable.iphone8));
        arrayDienthoai.add(new DienThoai("IPHONE 12" , "Thiết kế và màn hình", R.drawable.iphone12));
        arrayDienthoai.add(new DienThoai("IPHONE 13" , "Thiết kế và màn hình", R.drawable.iphone13));
    }
}