package com.minhpt.lab5_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Bai1 extends AppCompatActivity {
    String selectedItemSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        EditText edt_name = findViewById(R.id.edt_name);
        EditText edt_diachi = findViewById(R.id.edt_diachi);
        Spinner spn_chool = findViewById(R.id.spn_school);
        Button btn_submit = findViewById(R.id.btn_submit);

        ArrayList<School> list = new ArrayList<>();
        list.add(new School(R.mipmap.hanoi, "Hà Nội"));
        list.add(new School(R.mipmap.danang, "Đà Nẵng"));
        list.add(new School(R.mipmap.taynguyen, "Tây Nguyên"));
        list.add(new School(R.mipmap.hcm, "Hồ Chí Minh"));
        list.add(new School(R.mipmap.cantho, "Cần Thơ"));
        SchoolSpinnerAdapter schoolSpinnerAdapter = new SchoolSpinnerAdapter(this, list);
        spn_chool.setAdapter(schoolSpinnerAdapter);

        spn_chool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemSpinner = ((School) spn_chool.getItemAtPosition(position)).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putString("name", edt_name.getText().toString());
                bundle.putString("address", edt_diachi.getText().toString());
                bundle.putString("branch", selectedItemSpinner);

                intent.putExtras(bundle);
                setResult(1, intent);
                finish();
            }
        });
    }
}