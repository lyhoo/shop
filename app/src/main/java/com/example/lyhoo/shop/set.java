package com.example.lyhoo.shop;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class set extends AppCompatActivity {
    Button quit;
    TextView Name;
    CardView Address;
    CardView About;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        quit = findViewById(R.id.button);
        Name = findViewById(R.id.name);
        Address = findViewById(R.id.address);
        About = findViewById(R.id.about);
        person_info person = new person_info();

        Name.setText(person.getName());
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(set.this).setTitle("提示")
                        .setMessage("确定要退出吗？")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                person_info person = new person_info("","",-1);
                                person.addId(0);
                                Intent intent = new Intent(set.this,main_interface.class);
                                intent.putExtra("flag",3);
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("取消",null)
                        .create();
                dialog.show();
            }
        });
        Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(set.this,address_phone.class);
                startActivity(intent);
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(set.this,About.class);
                startActivity(intent);
            }
        });

    }
}
