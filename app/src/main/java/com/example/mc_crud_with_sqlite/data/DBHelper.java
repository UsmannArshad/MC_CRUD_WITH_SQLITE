package com.example.mc_crud_with_sqlite.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mc_crud_with_sqlite.models.StudentModel;
import com.example.mc_crud_with_sqlite.params.Params;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable  Context context)
    {
        super(context,"MyDB.db",null,Params.DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_query="CREATE TABLE " + Params.Table_Name + "(" +
                Params.Student_Id + " Integer PRIMARY KEY AUTOINCREMENT, " + Params.Student_Name + " Text, "
                + Params.Student_RollNumber + " TEXT, " + Params.Student_Active + " BOOL) ";
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
    public ArrayList<StudentModel> GetAllStudents()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String select_query="SELECT * FROM "+ Params.Table_Name ;
        //cursor maybe acts as cursor to table row at start it is at first row
        Cursor cursor=db.rawQuery(select_query,null);
        ArrayList<StudentModel> studentArrayList=new ArrayList<>();
        if(cursor.moveToFirst()){
                do {

                    studentArrayList.add(new StudentModel(cursor.getString(1),
                            cursor.getString(2),
                            cursor.getInt(3) == 1 ? true : false));
                } while (cursor.moveToNext());
        }
        cursor.close();
        return studentArrayList;
    }


}
