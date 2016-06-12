package com.pharmacy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pharmacy.R;
import com.pharmacy.conf.databases.DBHelper;
import com.pharmacy.domain.doctor.impl.DoctorImp;
import com.pharmacy.repository.doctor.impl.DoctorRepositoryImpl;

/**
 * Created by SONY on 2016-06-05.
 */
public class FirstScreen extends Activity
{
    DoctorRepositoryImpl myDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

    }

    public void onEnterName(View view)
    {
        Intent getNameScreenIntent = new Intent(this,SecondScreen.class);

        EditText patFName = (EditText)
                findViewById(R.id.editText2);
        EditText patLName = (EditText)
                findViewById(R.id.txtlast);
        EditText patNum = (EditText)
                findViewById(R.id.txtNum);

        String fn = String.valueOf(patFName.getText());
        String ln = String.valueOf(patLName.getText());
        String cn = String.valueOf(patNum.getText());

        String det = fn + " " + ln + "  "+ cn;
        DoctorImp createEntity = new DoctorImp.Builder()
                .bDocName(det)
                .build();

        getNameScreenIntent.putExtra("myValue",createEntity);

        startActivity(getNameScreenIntent);


        //myDoc.save(createEntity);
    }

}
