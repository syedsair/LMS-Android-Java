package com.example.flex;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CourseAdapter extends ArrayAdapter<Course> {
    private Context mContext;
    private Integer mResource;

    public CourseAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Course> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).name;
        String grade = getItem(position).grade;
        String points = getItem(position).points;
        String sec = getItem(position).section;
        String imp = getItem(position).importance;
        String ch = getItem(position).creditHour;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView Name = (TextView) convertView.findViewById(R.id.course_name_last2);
        TextView Grade = (TextView) convertView.findViewById(R.id.grade_course2);
        TextView Points = (TextView) convertView.findViewById(R.id.points_course2);
        TextView Sec = (TextView) convertView.findViewById(R.id.section_course2);
        TextView Imp = (TextView) convertView.findViewById(R.id.imp_course2);
        TextView Ch = (TextView) convertView.findViewById(R.id.chours_course2);

        Name.setText(name);
        Grade.setText(grade);
        Points.setText(points);
        Sec.setText(sec);
        Imp.setText(imp);
        Ch.setText(ch);

        return convertView;
    }
}
