package com.example.flex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SemesterAdapter extends ArrayAdapter<Semester> {
    private Context mContext;
    private Integer mResource;
    private ArrayList<Semester> sems;

    public SemesterAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Semester> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        sems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).SemesterName;
        ArrayList<Course> courses = new ArrayList<Course>();

        for (int i=0;i<sems.size();i++){
            if (sems.get(i).SemesterName.equals(name)){
                courses = sems.get(i).Courses;
            }
        }

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView sName = (TextView) convertView.findViewById(R.id.semester_name);

        TextView cname1 = (TextView) convertView.findViewById(R.id.course_name_last1);
        TextView cgrade1 = (TextView) convertView.findViewById(R.id.grade_course1);
        TextView cpoint1 = (TextView) convertView.findViewById(R.id.points_course1);
        TextView csec1 = (TextView) convertView.findViewById(R.id.section_course1);
        TextView cimp1 = (TextView) convertView.findViewById(R.id.imp_course1);

        TextView cname2 = (TextView) convertView.findViewById(R.id.course_name_last2);
        TextView cgrade2 = (TextView) convertView.findViewById(R.id.grade_course2);
        TextView cpoint2 = (TextView) convertView.findViewById(R.id.points_course2);
        TextView csec2 = (TextView) convertView.findViewById(R.id.section_course2);
        TextView cimp2 = (TextView) convertView.findViewById(R.id.imp_course2);

        TextView cname3 = (TextView) convertView.findViewById(R.id.course_name_last3);
        TextView cgrade3 = (TextView) convertView.findViewById(R.id.grade_course3);
        TextView cpoint3 = (TextView) convertView.findViewById(R.id.points_course3);
        TextView csec3 = (TextView) convertView.findViewById(R.id.section_course3);
        TextView cimp3 = (TextView) convertView.findViewById(R.id.imp_course3);

        TextView cname4 = (TextView) convertView.findViewById(R.id.course_name_last4);
        TextView cgrade4 = (TextView) convertView.findViewById(R.id.grade_course4);
        TextView cpoint4 = (TextView) convertView.findViewById(R.id.points_course4);
        TextView csec4 = (TextView) convertView.findViewById(R.id.section_course4);
        TextView cimp4 = (TextView) convertView.findViewById(R.id.imp_course4);

        TextView cname5 = (TextView) convertView.findViewById(R.id.course_name_last5);
        TextView cgrade5 = (TextView) convertView.findViewById(R.id.grade_course5);
        TextView cpoint5 = (TextView) convertView.findViewById(R.id.points_course5);
        TextView csec5 = (TextView) convertView.findViewById(R.id.section_course5);
        TextView cimp5 = (TextView) convertView.findViewById(R.id.imp_course5);


        sName.setText(name);

        cname1.setText(courses.get(0).name);
        cgrade1.setText(courses.get(0).grade);
        cpoint1.setText(courses.get(0).points);
        csec1.setText(courses.get(0).section);
        cimp1.setText(courses.get(0).importance);

        cname2.setText(courses.get(1).name);
        cgrade2.setText(courses.get(1).grade);
        cpoint2.setText(courses.get(1).points);
        csec2.setText(courses.get(1).section);
        cimp2.setText(courses.get(1).importance);

        cname3.setText(courses.get(2).name);
        cgrade3.setText(courses.get(2).grade);
        cpoint3.setText(courses.get(2).points);
        csec3.setText(courses.get(2).section);
        cimp3.setText(courses.get(2).importance);

        cname4.setText(courses.get(3).name);
        cgrade4.setText(courses.get(3).grade);
        cpoint4.setText(courses.get(3).points);
        csec4.setText(courses.get(3).section);
        cimp4.setText(courses.get(3).importance);

        cname5.setText(courses.get(4).name);
        cgrade5.setText(courses.get(4).grade);
        cpoint5.setText(courses.get(4).points);
        csec5.setText(courses.get(4).section);
        cimp5.setText(courses.get(4).importance);

        return convertView;
    }
}
