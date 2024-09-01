package com.example.gpskhushi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MySqlHelper extends SQLiteOpenHelper {

    Context cont;
    public String dbPath="/data/data/com.example.gpskhushi/databases/";
    public static String dbName= "gps3.db";
    SQLiteDatabase db = null;
    String mypath = dbPath+dbName;
    public MySqlHelper(Context cont)
    {
        super(cont,dbName,null,1);
        this.cont = cont;
    }

    public void checkDatabase()
    {

        try
        {
            db = SQLiteDatabase.openDatabase(mypath,null,SQLiteDatabase.OPEN_READWRITE);
        }
        catch (Exception e)
        {
            Toast.makeText(cont, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if(db==null)
        {
            this.getWritableDatabase();
            this.close();

            try
            {
                InputStream is = cont.getAssets().open(dbName);
                OutputStream os = new FileOutputStream(mypath);
                byte b[] = new byte[1024];

                while (is.read(b)>0)
                {
                    os.write(b);
                }
                os.close();
                is.close();
                Toast.makeText(cont, "Database Created", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                Toast.makeText(cont, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(cont, "Databse already exists", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertValues(String sr,String date,String time,String area,String city,String pin)
    {
        db = SQLiteDatabase.openDatabase(mypath,null,SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into location values("+sr+","+"'"+date+"'"+","+"'"+time+"'"+","+"'"+area+"'"+","+"'"+city+"'"+","+pin+")");
        db.close();
    }

    public void deleteValues()
    {
        db = SQLiteDatabase.openDatabase(mypath,null,SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("delete from location");
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Do nothing
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}