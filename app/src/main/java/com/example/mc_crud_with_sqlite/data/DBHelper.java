package com.example.mc_crud_with_sqlite.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mc_crud_with_sqlite.models.StudentModel;
import com.example.mc_crud_with_sqlite.params.Params;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context)
    {
        super(context,Params.DataBase_Name,null,Params.DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_query="CREATE TABLE " + Params.Table_Name + "(" + Params.Student_Id + " INTEGER PRIMARY KEY, " + Params.Student_Name + " TEXT, " +Params.Student_RollNumber + " TEXT UNIQUE, "+ Params.Student_Active + " Boolean "+ ")";
        Log.d("dbquery","query is"+create_query);
        sqLiteDatabase.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void AddStudent(StudentModel s)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(Params.Student_Name,s.getName());
        values.put(Params.Student_RollNumber,s.getRollNumber());
        values.put(Params.Student_Active,s.isActive());
        db.insert(Params.Table_Name,null,values);
        Log.d("dbquery","studentadded");
    }

}
