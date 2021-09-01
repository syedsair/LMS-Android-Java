package com.example.flex;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Semester {
    String SemesterName;
    ArrayList<Course> Courses;

    public Semester(String name, ArrayList<Course> c){
        SemesterName = name;
        Courses = c;
    }

}
