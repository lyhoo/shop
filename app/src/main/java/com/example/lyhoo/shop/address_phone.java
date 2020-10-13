package com.example.lyhoo.shop;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class address_phone extends AppCompatActivity {
    TextView Name;
    TextView Phone;
    TextView Address;
    ImageView Return;
    Button Submit;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_phone);

        Name = (TextView) findViewById(R.id.name);
        Phone = (TextView) findViewById(R.id.phone);
        Address = (TextView) findViewById(R.id.address);
        Return = (ImageView) findViewById(R.id.fanhui);
        Submit = (Button) findViewById(R.id.button);

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",0);

        final person_info personInfo = new person_info();

        if (personInfo.getConsignee().equals("0")){
            Name.setText(personInfo.getName());
        }else{
            Name.setText(personInfo.getConsignee());
        }
        if (personInfo.getAddress().equals("0")){
            Address.setHint("请填写收件人地址！");
        }else{
            Address.setText(personInfo.getAddress());
        }
        if (personInfo.getPhone().equals("0")){
            Phone.setHint("请填写收件人联系方式！");
        }else{
            Phone.setText(personInfo.getPhone());
        }

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Address.getText().toString().equals("")||Phone.getText().toString().equals("")){
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(address_phone.this).setTitle("提示")
                            .setMessage("请填写完整")
                            .setNegativeButton("确定",null)
                            .create();
                    dialog.show();
                }else{
                    personInfo.addConsignee(Name.getText().toString());
                    personInfo.addPhone(Phone.getText().toString());
                    personInfo.addAddress(Address.getText().toString());
                    Myhelper myhelper = new Myhelper(address_phone.this);
                    SQLiteDatabase db = myhelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("address",Address.getText().toString());
                    contentValues.put("phone",Phone.getText().toString());
                    contentValues.put("consignee",Name.getText().toString());
                    db.update("person",contentValues,"id=?",new String[]{personInfo.getId()+""});
                    db.close();
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(address_phone.this).setTitle("提示")
                            .setMessage("修改成功")
                            .setNegativeButton("确定",null)
                            .setPositiveButton("返回", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(address_phone.this,set.class);
                                    startActivity(intent);
                                }
                            })
                            .create();
                    dialog.show();
                }
            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag==0){
                    Intent intent = new Intent(address_phone.this,set.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(address_phone.this,main_interface.class);
                    intent.putExtra("flag",8);
                    startActivity(intent);
                }

            }
        });
    }
}
