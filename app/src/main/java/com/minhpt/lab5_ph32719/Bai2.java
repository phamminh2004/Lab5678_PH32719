package com.minhpt.lab5_ph32719;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Bai2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        ListView lv_student = findViewById(R.id.lv_student);
        Button btn_add = findViewById(R.id.btn_add);

        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("Hà Nội", "Phạm Minh", "Vĩnh Phúc"));
        list.add(new Student("Đà Nẵng", "Quốc Anh", "Vĩnh Phúc"));
        list.add(new Student("Tây Nguyên", "Văn Quân", "Hà Nội"));
        list.add(new Student("Cần Thơ", " Phạm Linh", "Cần Thơ"));

        ListStudentAdapter listStudentAdapter = new ListStudentAdapter(this, list);

        lv_student.setAdapter(listStudentAdapter);

        ActivityResultLauncher<Intent> getData = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 2) {
                            Intent intent = result.getData();
                            Bundle bundle = intent.getExtras();
                            String name = bundle.getString("name");
                            String address = bundle.getString("address");
                            String branch = bundle.getString("branch");
                            list.add(new Student(branch, name, address));
                            listStudentAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bai2.this, Bai1.class);
                getData.launch(intent);
            }
        });
    }
}