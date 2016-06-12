package com.pharmacy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.pharmacy.R;
import com.pharmacy.conf.databases.DBHelper;
import com.pharmacy.domain.doctor.impl.DoctorImp;
import com.pharmacy.repository.doctor.impl.DoctorRepositoryImpl;

import java.util.Set;

/**
 * Created by SONY on 2016-06-05.
 */
public class SecondScreen extends Activity
{
    Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent actCall = getIntent();
        final DoctorImp myCatch = (DoctorImp)actCall.getSerializableExtra("myValue");

        //String preActivity = actCall.getExtras().getString("callingActivity");
        TextView callActMsg = (TextView) findViewById(R.id.textView4);
        callActMsg.append(" " + myCatch.toString());

        DoctorRepositoryImpl myDocRepo = new DoctorRepositoryImpl(getApplicationContext());
        DoctorImp myDoc = myDocRepo.save(myCatch);
        ViewPat();
    }

   public void ViewPat()
    {
        buttonAdd = (Button) findViewById(R.id.btnViewP);
        buttonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*DoctorRepositoryImpl myDocRepo = new DoctorRepositoryImpl(getApplicationContext());
                DoctorImp myDoc = myDocRepo.save(myCatch);*/
                Intent intent = new Intent(SecondScreen.this,ThirdScreen.class);
                startActivity(intent);
            }
        });
    }
}
