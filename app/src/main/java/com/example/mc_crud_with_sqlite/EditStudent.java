package com.example.mc_crud_with_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mc_crud_with_sqlite.data.DBHelper;
import com.example.mc_crud_with_sqlite.models.StudentModel;

public class EditStudent extends AppCompatActivity {

    Button updatebtn,deletebtn;
    EditText editname,editrollno;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        updatebtn=findViewById(R.id.updatebutton);
        deletebtn=findViewById(R.id.deletebutton);
        editname=findViewById(R.id.updatenameedittext);
        editrollno=findViewById(R.id.updaterollnoedittext);
        switch1=findViewById(R.id.switch2);
        Intent intent=getIntent();
        Bundle Student=intent.getExtras();
        StudentModel currentstudent=new StudentModel(Student.getString("Name"),Student.getString("Rollno"),Student.getBoolean("IsActive"));
        editname.setText(currentstudent.getName());
        editrollno.setText(currentstudent.getRollNumber());
        switch1.setChecked(currentstudent.isActive());

        deletebtn.setOnClickListener(new View.OnClickListener() {
            DBHelper dbHelper=new DBHelper(EditStudent.this);
            @Override
            public void onClick(View view) {
                dbHelper.DeleteStudent(currentstudent.getRollNumber());
                Intent intent=new Intent(EditStudent.this,MainActivity.class);
                startActivity(intent);
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            DBHelper dbHelper=new DBHelper(EditStudent.this);
            StudentModel studentModel;
            @Override
            public void onClick(View view) {
                try {
                    studentModel= new StudentModel(editname.getText().toString(), editrollno.getText().toString(), switch1.isChecked());
                }
                catch (Exception e){
                    Toast.makeText(EditStudent.this, "Error", Toast.LENGTH_SHORT).show();
                }
                dbHelper.UpdateStudent(studentModel,currentstudent.getRollNumber());
                Intent intent=new Intent(EditStudent.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}