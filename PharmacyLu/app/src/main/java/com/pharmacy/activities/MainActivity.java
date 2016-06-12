package com.pharmacy.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pharmacy.R;
import com.pharmacy.conf.databases.DBHelper;

public class MainActivity extends AppCompatActivity
{
    Button buttonBegin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onBegin();
    }

    public void onBegin()
    {
        buttonBegin = (Button) findViewById(R.id.btnBigin);
        buttonBegin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,FirstScreen.class);
                startActivity(intent);

            }
        });
    }

}