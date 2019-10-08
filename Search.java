package com.example.prateeksaxena.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {
LinearLayout ll;
EditText t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        t=findViewById(R.id.st);
        ll=findViewById(R.id.ll);



        t.addTextChangedListener(new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ll.removeAllViews();
                SQLiteDatabase db=openOrCreateDatabase("sms",MODE_PRIVATE,null);
                db.execSQL("create table if not exists students(rno int,name varchar(30),marks float)");
                //Toast.makeText(Search.this, ""+t.getText().toString(), Toast.LENGTH_SHORT).show();
                String query="select * from students where name like '%"+t.getText().toString()+"%'";
                Cursor c=db.rawQuery(query,null);
                if(c.moveToFirst())
                {
                    do{
                    String rno=c.getString(0);
                    String name=c.getString(1);
                    String marks=c.getString(2);
                        TextView tv=new TextView(Search.this);
                        tv.setText(rno+" "+name+" "+marks);
                        ll.addView(tv);
                }while (c.moveToNext());
                }
                else
                {
                    Toast.makeText(Search.this, "Empty data", Toast.LENGTH_SHORT).show();
                }
                db.close();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
