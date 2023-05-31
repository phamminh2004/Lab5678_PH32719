package com.minhpt.lab5_ph32719;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ListStudentAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private ArrayList<Student> list, listOld;

    public ListStudentAdapter(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
        this.listOld = list;
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if (s.isEmpty()) {
                    list = listOld;
                } else {
                    ArrayList<Student> listS = new ArrayList<>();
                    for (Student st : listOld) {
                        if (st.getName().toLowerCase().contains(s.toLowerCase())){
                            listS.add(st);
                        }
                    }
                    list = listS;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Student>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}