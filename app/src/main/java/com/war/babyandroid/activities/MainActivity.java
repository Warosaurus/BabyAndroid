package com.war.babyandroid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.war.babyandroid.R;
import com.war.babyandroid.adapters.StringListAdapter;
import com.war.babyandroid.database.DatabaseHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> stringArrayList;
    StringListAdapter arrayAdapter;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);

        stringArrayList = databaseHandler.readString();

        ListView stringList = (ListView) findViewById(R.id.stringList);
        arrayAdapter = new StringListAdapter(this, R.layout.list_string_listview, stringArrayList);
        stringList.setAdapter(arrayAdapter);

        final EditText addStringET = (EditText) findViewById(R.id.addStringET);

        Button addStringButton = (Button) findViewById(R.id.addStringButton);
        addStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!addStringET.getText().toString().isEmpty()){
                    stringArrayList.add(addStringET.getText().toString());

                    databaseHandler.insertString(addStringET.getText().toString());

                    arrayAdapter.notifyDataSetChanged();

                    addStringET.getText().clear();

                }
            }
        });


    }
}
