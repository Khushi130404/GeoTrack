package com.example.gpskhushi;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ShowActivity extends Activity {

    public String dbPath="/data/data/com.example.gpskhushi/databases/";
    public static String dbName= "gps3.db";
    SQLiteDatabase db = null;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv = findViewById(R.id.lv);

        String myPath = dbPath+dbName;
        try
        {
            db = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Cursor cur = db.rawQuery("select * from location",null);
        String data[] = new String[cur.getCount()];
        String time,date,sr,add,city,pin;
        for (int i=0; cur.moveToNext(); i++)
        {
            sr = ""+cur.getInt(0);
            date = cur.getString(1);
            time = cur.getString(2);
            add = cur.getString(3);
            city = cur.getString(4);
            pin = ""+cur.getInt(5);
            data[i] = sr+"  "+date+"  "+time+"  "+add+"  "+city+"  "+pin;
        }
        db.close();
        ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,data);
        lv.setAdapter(ad);
    }
}