package com.minhpt.lab5_ph32719;

import android.app.Activity;
import android.content.Context;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ListStudentAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Student> list;

    public ListStudentAdapter(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_listview, parent, false);

        TextView branch = convertView.findViewById(R.id.branch);
        TextView name = convertView.findViewById(R.id.name);
        TextView address = convertView.findViewById(R.id.address);
        Button btn_delete = convertView.findViewById(R.id.btn_delete);
        Button btn_update = convertView.findViewById(R.id.btn_update);

        branch.setText("Fpoly " + list.get(position).getBranch());
        name.setText("Họ tên: " + list.get(position).getName());
        address.setText("Địa chỉ: " + list.get(position).getAddress());

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return convertView;
    }
}
