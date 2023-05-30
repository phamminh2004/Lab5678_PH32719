package com.minhpt.lab5_ph32719;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SchoolSpinnerAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<School> list;

    public SchoolSpinnerAdapter(Context context, ArrayList<School> list) {
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
        convertView = inflater.inflate(R.layout.item_spinner, parent, false);
        ImageView img = convertView.findViewById(R.id.img);
        TextView name = convertView.findViewById(R.id.name);

        img.setImageResource(list.get(position).getImg());
        name.setText("Foly " + list.get(position).getName());

        return convertView;
    }
}
