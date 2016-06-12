package com.pharmacy.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.pharmacy.R;
import com.pharmacy.conf.databases.DBHelper;
import com.pharmacy.domain.doctor.impl.DoctorImp;
import com.pharmacy.repository.doctor.DoctorRepository;
import com.pharmacy.repository.doctor.impl.DoctorRepositoryImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SONY on 2016-06-06.
 */
public class ThirdScreen extends Activity
{
    ScrollView view;
    Set<DoctorImp> myDoc;

    TextView txtV;

    Button buttonHome;
    //Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        buttonHome = (Button) findViewById(R.id.btnHome);
       // buttonClear = (Button) findViewById(R.id.btnClear);

        buttonHome.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(ThirdScreen.this,MainActivity.class);
                    startActivity(intent);

                }
            });
       /*buttonClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DoctorRepository repo = new DoctorRepositoryImpl(getApplicationContext());
                repo.deleteAll();
                Intent intent = new Intent(ThirdScreen.this,ThirdScreen.class);
                startActivity(intent);

            }
        });*/

        DoctorRepository repo = new DoctorRepositoryImpl(getApplicationContext());
        //Read All
        myDoc = repo.findAll();
       /* Toast.makeText(ThirdScreen.this,myDoc.toString(),
                Toast.LENGTH_LONG).show();*/

        addData();
    }


    public void addData()
    {
        for (DoctorImp myClient : myDoc)
        {

            String idNum = myClient.getDoctorID().toString();
            String nameD = myClient.getDoctorName().toString();

            txtV = (TextView) findViewById(R.id.listV);
            txtV.append(/*" " /*+ idNum + */" " + nameD+"\n------------------------------------------------------\n" );
        }
    }


}