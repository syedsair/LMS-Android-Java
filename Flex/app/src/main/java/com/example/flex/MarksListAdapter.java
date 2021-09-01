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

public class MarksListAdapter extends ArrayAdapter<MarksObject> {
    private Context mContext;
    private Integer mResource;

    public MarksListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MarksObject> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Name = getItem(position).Name;

        String m1 = getItem(position).m1;
        String m2 = getItem(position).m2;
        String f = getItem(position).f;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView cName = (TextView) convertView.findViewById(R.id.course_name);
        TextView m1Marks = (TextView) convertView.findViewById(R.id.mid_1);
        TextView m2Marks = (TextView) convertView.findViewById(R.id.mid_2);
        TextView fMarks = (TextView) convertView.findViewById(R.id.mid_3);

        cName.setText(Name);
        m1Marks.setText(m1);
        m2Marks.setText(m2);
        fMarks.setText(f);

        return convertView;
    }
}
