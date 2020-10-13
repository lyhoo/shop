package com.example.lyhoo.shop;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText s_password;
    Button submit;
    TextView empty_button;
    TextView login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        s_password = findViewById(R.id.editText3);
        submit = findViewById(R.id.button1);
        empty_button = findViewById(R.id.textView1);
        login_button = findViewById(R.id.textView2);

        empty_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
                password.setText("");
                s_password.setText("");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UserName = username.getText().toString();
                final String PassWord = password.getText().toString();
                String S_PassWord = s_password.getText().toString();
                if (UserName.equals("")||PassWord.equals("")||S_PassWord.equals("")){
                    UtilTool.toast(register.this, "请填写完整！");
                    Toast.makeText(register.this,"请填写完整！",Toast.LENGTH_SHORT);
                }else if (!PassWord.equals(S_PassWord)){
                    UtilTool.toast(register.this, "两次填写的密码不一致，请重新填写！");
                    Toast.makeText(register.this,"两次填写的密码不一致，请重新填写！",Toast.LENGTH_SHORT);
                    username.setText("");
                    password.setText("");
                    s_password.setText("");
                }else {
                    Myhelper myhelper = new Myhelper(register.this);
                    SQLiteDatabase w_db = myhelper.getWritableDatabase();
                    SQLiteDatabase r_db = myhelper.getReadableDatabase();
                    Cursor cursor = r_db.query("person",null,"name=?",new String[]{UserName},null,null,null);
                    if (cursor.getCount()==0){
                        ContentValues values = new ContentValues();
                        values.put("name",UserName);
                        values.put("password",PassWord);
                        w_db.insert("person",null,values);
                        cursor.close();
                        w_db.close();
                        r_db.close();
                        AlertDialog dialog;
                        dialog = new AlertDialog.Builder(register.this).setTitle("提示")
                                .setMessage("注册成功！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Myhelper myhelper = new Myhelper(register.this);
                                        SQLiteDatabase db;
                                        db = myhelper.getReadableDatabase();
                                        Cursor cursor = db.rawQuery("SELECT id,name,password,address,phone,consignee FROM person WHERE name=?",new String[]{UserName});
                                        cursor.moveToNext();
                                        int QuerryId = cursor.getInt(0);
                                        String Address = cursor.getString(3);
                                        String Phone = cursor.getString(4);
                                        String Consignee = cursor.getString(5);
                                        cursor.close();
                                        db.close();
                                        person_info person = new person_info(UserName,PassWord,QuerryId);
                                        person.addAddress(Address);
                                        person.addPhone(Phone);
                                        person.addConsignee(Consignee);
                                        Intent intent;
                                        intent = new Intent(register.this,main_interface.class);
                                        intent.putExtra("flag",2);
                                        startActivity(intent);
                                    }
                                }).create();
                        dialog.show();
                    }else{
                        AlertDialog dialog;
                        dialog = new AlertDialog.Builder(register.this).setTitle("提示")
                                .setMessage("此账号已存在，请重新输入或直接登录！")
                                .setNegativeButton("确定",null)
                                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent;
                                        intent = new Intent(register.this,login.class);
                                        intent.putExtra("flag",2);
                                        startActivity(intent);
                                    }
                                }).create();
                        dialog.show();
                        username.setText("");
                        password.setText("");
                        s_password.setText("");
                    }
                }
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this,login.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(register.this,main_interface.class);
            intent.putExtra("flag",3);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
