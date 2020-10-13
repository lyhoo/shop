package com.example.lyhoo.shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    TextView username;
    TextView password;
    Button submit;
    TextView lost_button;
    TextView register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        submit = findViewById(R.id.button1);
        lost_button = findViewById(R.id.textView1);
        register_button = findViewById(R.id.textView2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("")||password.getText().toString().equals(""))
                {
                    UtilTool.toast(login.this, "请填写完整！");
                    Toast.makeText(login.this,"请填写完整！",Toast.LENGTH_SHORT);
                }else {
                    String UserName;
                    String QuerryName;
                    String PassWord;
                    String QuerryPassword;
                    String Address;
                    String Phone;
                    String Consignee;
                    int QuerryId;
                    SQLiteDatabase db;
                    Myhelper myhelper = new Myhelper(login.this);
                    db = myhelper.getReadableDatabase();
                    UserName = username.getText().toString();
                    PassWord = password.getText().toString();
                    Cursor cursor = db.rawQuery("SELECT id,name,password,address,phone,consignee FROM person WHERE name=?",new String[]{UserName});
                    if (cursor.getCount()==0)
                    {
                        AlertDialog dialog;
                        dialog = new AlertDialog.Builder(login.this).setTitle("提示")
                                .setMessage("该用户名不存在，请检查用户名或注册该用户！")
                                .setNegativeButton("确定",null)
                                .setPositiveButton("注册", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent;
                                        intent = new Intent(login.this,register.class);
                                        intent.putExtra("flag",2);
                                        startActivity(intent);
                                    }
                                }).create();
                        dialog.show();
                        username.setText("");
                        password.setText("");
                    }else {
                        cursor.moveToNext();
                        QuerryName = cursor.getString(1);
                        QuerryPassword = cursor.getString(2);
                        QuerryId = cursor.getInt(0);
                        Address = cursor.getString(3);
                        Phone = cursor.getString(4);
                        Consignee = cursor.getString(5);
                        cursor.close();
                        db.close();
                        if (QuerryName.equals(UserName)){
                            person_info person = new person_info(UserName,PassWord,QuerryId);
                            person.addAddress(Address);
                            person.addPhone(Phone);
                            person.addConsignee(Consignee);
//                            person.addId(QuerryId);
//                            AlertDialog dialog;
//                            dialog = new AlertDialog.Builder(login.this).setTitle("提示")
//                                    .setMessage("ID为"+QuerryId)
//                                    .setPositiveButton("确定",null).create();
//                            dialog.show();
                            Intent intent = new Intent();
                            intent.putExtra("flag",1);
                            intent.setClass(login.this,main_interface.class);
                            startActivity(intent);
                        }else {
                            AlertDialog dialog;
                            dialog = new AlertDialog.Builder(login.this).setTitle("提示")
                                    .setMessage("密码错误！")
                                    .setPositiveButton("确定",null).create();
                            dialog.show();
                            password.setText("");
                        }
                    }
                }
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,register.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(login.this,main_interface.class);
            intent.putExtra("flag",3);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
