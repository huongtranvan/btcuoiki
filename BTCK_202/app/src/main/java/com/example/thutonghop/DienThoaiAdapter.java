package com.example.thutonghop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thutonghop.DienThoai;

import java.util.List;

public class DienThoaiAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DienThoai> dienThoaiList;

    public DienThoaiAdapter(Context context, int layout, List<DienThoai> dienThoaiList) {
        this.context = context;
        this.layout = layout;
        this.dienThoaiList = dienThoaiList;
    }

    @Override
    public int getCount() {
        return dienThoaiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        // ánh xạ
        TextView textTen = (TextView) view.findViewById(R.id.textviewTen);
        TextView textMoTa = (TextView) view.findViewById(R.id.textviewMoTa);
        ImageView imgHinh = (ImageView) view.findViewById(R.id.imageviewHinh);
        // gán giá trị
        DienThoai dienthoai = dienThoaiList.get(i);
        textTen.setText(dienthoai.getTen());
        textMoTa.setText(dienthoai.getMoTa());
        imgHinh.setImageResource(dienthoai.getHinh());
        return view;
    }
}
