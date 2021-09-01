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

public class AnnouncementAdapter extends ArrayAdapter<announcementObj> {
    private Context mContext;
    private Integer mResource;

    public AnnouncementAdapter(@NonNull Context context, int resource, @NonNull ArrayList<announcementObj> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String data = getItem(position).Announcement;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView cName = (TextView) convertView.findViewById(R.id.announce);

        cName.setText(data);

        return convertView;
    }
}
