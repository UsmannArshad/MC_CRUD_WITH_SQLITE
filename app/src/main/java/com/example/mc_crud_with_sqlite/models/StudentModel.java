package com.example.mc_crud_with_sqlite.models;

public class StudentModel {
    private String name;
    private String rollNumber;
    private boolean isActive;

    @Override
    public String toString() {
        return "StudentModel{" +
                "name='" + name + '\'' +
                ", rollNmber=" + rollNumber +
                ", isEnroll=" + isActive +
                '}';
    }

    public StudentModel(String name1, String rollno, boolean isactive) {
        name = name1;
        rollNumber = rollno;
        isActive = isactive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
