package com.example.prateeksaxena.data;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button b1,b2,b3,bs,b4;
EditText t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.et1);
        t2=findViewById(R.id.et2);
        t3=findViewById(R.id.et3);
        b1=findViewById(R.id.b);
        b4=findViewById(R.id.b4);
        bs=findViewById(R.id.bs);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().isEmpty() || t2.getText().toString().isEmpty() || t3.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter the details correctly", Toast.LENGTH_SHORT).show();}
                SQLiteDatabase db=openOrCreateDatabase("sms",MODE_PRIVATE,null);
                db.execSQL("create table if not exists students(rno int,name varchar(30), marks float) ");
                db.execSQL("insert into students values("+t1.getText().toString()+",'"+t2.getText().toString()+"',"+t3.getText().toString()+")");
                db.close();
                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().isEmpty() || t2.getText().toString().isEmpty() || t3.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter the details correctly", Toast.LENGTH_SHORT).show();}
                SQLiteDatabase db=openOrCreateDatabase("sms",MODE_PRIVATE,null);
                db.execSQL("create table if not exists students(rno int,name varchar(30), marks float) ");
                String query="update students set name='"+t2.getText().toString()+"',marks="+t3.getText().toString()+" where rno="+t1.getText().toString();
                db.execSQL(query);
                db.close();
                Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=openOrCreateDatabase("sms",MODE_PRIVATE,null);
                db.execSQL("create table if not exists students(rno int,name varchar(30), marks float) ");
                String query="delete from students where rno="+t1.getText().toString();
                t1.setText("");
                t2.setText("");
                t3.setText("");
                db.execSQL(query);
                db.close();
                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter the details correctly", Toast.LENGTH_SHORT).show();}
                SQLiteDatabase db=openOrCreateDatabase("sms",MODE_PRIVATE,null);
                db.execSQL("create table if not exists students(rno int,name varchar(30),marks float)");
                String query="select * from students where rno="+t1.getText().toString();
                Cursor c=db.rawQuery(query,null);
                if(c.moveToFirst())
                {
                    t2.setText(c.getString(1));
                    t3.setText(c.getString(2));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid rno", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,Search.class);
                startActivity(in);


            }
        });

    }
}
