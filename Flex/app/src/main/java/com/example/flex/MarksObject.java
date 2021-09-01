package com.example.flex;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;



class MarksObject implements Serializable {
    String Name;
    String m1, m2, f;

    public MarksObject() {
        Name = "Empty";
        m1 = m2 = f = "0";
    }
    public void setM1(String marks){
        m1 = marks;
    }
    public void setM2(String marks){
        m2 = marks;
    }
    public void setF(String marks){
        f = marks;
    }

    public MarksObject(String name,String M1, String M2,String F) {
        Name = name;
        m1 = M1;
        m2 = M2;
        f = F;
    }

    public String getName() {
        return Name;
    }

    public String getM1() {
        return m1;
    }
    public String getM2() {
        return m2;
    }
    public String getF() {
        return f;
    }


}