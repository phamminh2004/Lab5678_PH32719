package com.minhpt.lab5_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DangNhap extends AppCompatActivity {

    String username;
    String password;
    CheckBox chk_remember;
    EditText edt_username;
    EditText edt_password;
    Context context = this;

    public List<User> readUser(Context context, String fileName) {
        List<User> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (List<User>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void remember(String user, String pass, boolean chk_remember) {
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", user);
        editor.putString("pass", pass);
        editor.putBoolean("chk_remember", chk_remember);
        editor.apply();
    }

    public void checkRemember() {
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        String pass = sharedPreferences.getString("pass", "");
        boolean chk_remember1 = sharedPreferences.getBoolean("chk_remember", false);
        chk_remember.setChecked(chk_remember1);
        if (chk_remember.isChecked()) {
            edt_username.setText(user);
            edt_password.setText(pass);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        Button btn_dangnhap = findViewById(R.id.btn_dangnhap);
        Button btn_dangky = findViewById(R.id.btn_dangky);
        chk_remember = findViewById(R.id.chk_remember);

        checkRemember();

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            username = bundle.getString(DangKy.KEY_USERNAME);
//            password = bundle.getString(DangKy.KEY_PASSWORD);
//        }
//        edt_username.setText(username);
//        edt_password.setText(password);


        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this, DangKy.class));
            }
        });
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> list = new ArrayList<>();
                list = readUser(context, "user.txt");

                String name = edt_username.getText().toString();
                String pass = edt_password.getText().toString();

                if (name.trim().isEmpty()) {
                    Toast.makeText(DangNhap.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
                } else if (pass.trim().isEmpty()) {
                    Toast.makeText(DangNhap.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (!name.equals(list.get(0).getUsername()) && !pass.equals(list.get(0).getPassword())) {
                    Toast.makeText(DangNhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    remember(list.get(0).getUsername(),list.get(0).getPassword(),true);
                    startActivity(new Intent(DangNhap.this, Bai2.class));
                    Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}