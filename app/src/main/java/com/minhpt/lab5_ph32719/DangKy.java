package com.minhpt.lab5_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DangKy extends AppCompatActivity {
    public static String KEY_USERNAME = "username";
    public static String KEY_PASSWORD = "password";
    Context context = this;

    public void writeUser(Context context, String fileName, User user) {
        List<User> list = new ArrayList<>();
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            list.add(user);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        EditText edt_username = findViewById(R.id.edt_username);
        EditText edt_password = findViewById(R.id.edt_password);
        EditText edt_comfirmpass = findViewById(R.id.edt_comfirmpass);
        Button btn_dangky = findViewById(R.id.btn_dangky);

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                String nhaplai = edt_comfirmpass.getText().toString();
                if (username.trim().isEmpty()) {
                    Toast.makeText(DangKy.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
                } else if (password.trim().isEmpty()) {
                    Toast.makeText(DangKy.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (nhaplai.trim().isEmpty()) {
                    Toast.makeText(DangKy.this, "Vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (!nhaplai.equals(password)) {
                    Toast.makeText(DangKy.this, "Mật khẩu nhập lại sai", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DangKy.this, DangNhap.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_USERNAME, username);
                    bundle.putString(KEY_PASSWORD, password);
                    intent.putExtras(bundle);
                    writeUser(context, "account.txt", new User(edt_username.getText().toString(), edt_password.getText().toString()));
                    startActivity(intent);
                }
            }
        });
    }
}