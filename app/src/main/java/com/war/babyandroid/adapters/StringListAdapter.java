package com.war.babyandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.war.babyandroid.R;

import java.util.ArrayList;

public class StringListAdapter extends ArrayAdapter<String>{

    private Context context;
    private ArrayList<String> stringList;
    private int resourceId;

    public StringListAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        stringList = objects;
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(resourceId, null);
        }

        if(!stringList.isEmpty()){
            final String value = stringList.get(position);

            TextView stringValue = (TextView) view.findViewById(R.id.stringValue);
            stringValue.setText(value);

            stringValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Toasted!", Toast.LENGTH_LONG).show();
                }
            });

        }

        return view;

    }
}
