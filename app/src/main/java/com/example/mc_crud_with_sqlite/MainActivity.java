package com.example.mc_crud_with_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mc_crud_with_sqlite.data.DBHelper;
import com.example.mc_crud_with_sqlite.models.StudentModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonadd,buttonview;
    EditText editname,editrollno;
    Switch switch1;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonadd=findViewById(R.id.addbutton);
        buttonview=findViewById(R.id.viewbutton);
        editname=findViewById(R.id.editname);
        editrollno=findViewById(R.id.editrollno);
        switch1=findViewById(R.id.switch1);
        listView=findViewById(R.id.listview1);

        buttonadd.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;
            @Override
            public void onClick(View view) {
                try {
                    studentModel= new StudentModel(editname.getText().toString(), editrollno.getText().toString(), switch1.isChecked());
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                DBHelper dbHelper  = new DBHelper(MainActivity.this);
                dbHelper.AddStudent(studentModel);
            }
        });
        buttonview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper=new DBHelper(MainActivity.this);
                ArrayList<StudentModel> studentarray=new ArrayList<>();
                studentarray=dbHelper.GetAllStudents();
                ArrayAdapter<StudentModel> adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,studentarray);
                listView.setAdapter(adapter);
            }
        });
    }
}