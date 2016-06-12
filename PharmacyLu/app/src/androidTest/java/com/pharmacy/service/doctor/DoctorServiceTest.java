package com.pharmacy.service.doctor;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.pharmacy.domain.doctor.impl.DoctorImp;

/**
 * Created by SONY on 2016-05-08.
 */
public class DoctorServiceTest extends AndroidTestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

        Intent intent = new Intent(this.getContext(), DoctorImp.class);
        intent.putExtra("NAME","Josh");
        this.getContext().startService(intent);
    }
}
