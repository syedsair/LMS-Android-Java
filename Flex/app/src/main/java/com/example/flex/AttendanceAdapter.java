package com.example.flex;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.Calendar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class AttendanceAdapter extends ArrayAdapter<AttendanceObject> {
    private Context mContext;
    private Integer mResource;

    public AttendanceAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AttendanceObject> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String c = getItem(position).courseName;
        String a = getItem(position).attendance;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView cName = (TextView) convertView.findViewById(R.id.course_name);
        TextView at = (TextView) convertView.findViewById(R.id.attendance_array);

        cName.setText(c);
        at.setText(a);

        return convertView;
    }
}
