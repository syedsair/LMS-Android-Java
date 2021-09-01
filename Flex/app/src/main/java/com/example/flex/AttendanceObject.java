package com.example.flex;

public class AttendanceObject {
    String courseName, attendance;

    public AttendanceObject(String c, String a){
        courseName = c;
        attendance = a;
    }

    public void setAttr(String course, String a){
        courseName = course;
        attendance = a;
    }
    public String getCourseName(){
        return courseName;
    }
    public String getAttendance(){
        return attendance;
    }
}
