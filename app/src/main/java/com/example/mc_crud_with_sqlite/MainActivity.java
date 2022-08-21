package com.example.mc_crud_with_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    ArrayList<StudentModel> studentarray;
    ArrayAdapter<StudentModel> adapter;
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
                studentarray=new ArrayList<>();
                studentarray=dbHelper.GetAllStudents();
                adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,studentarray);
                listView.setAdapter(adapter);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StudentModel s=studentarray.get(i);
                Intent intent=new Intent(MainActivity.this,EditStudent.class);
                Bundle Student=new Bundle();
                Student.putString("Name",s.getName());
                Student.putString("Rollno",s.getRollNumber());
                Student.putBoolean("IsActive",s.isActive());
                intent.putExtras(Student);
                startActivity(intent);
//                AlertDialog a=new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("Edit Student")
//                        .setMessage("Choose an option from below!")
//                        .setCancelable(true)
//                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                StudentModel s=studentarray.get(i);
//                                dbHelper.DeleteStudent(s.getRollNumber());
//                                studentarray=dbHelper.GetAllStudents();
//                                adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,studentarray);
//                                listView.setAdapter(adapter);
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                           @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .show();
            }

        });
    }
}