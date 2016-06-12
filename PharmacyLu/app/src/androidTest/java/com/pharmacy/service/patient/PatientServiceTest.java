package com.pharmacy.service.patient;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.pharmacy.domain.patient.impl.PatientImpl;

/**
 * Created by SONY on 2016-05-08.
 */
public class PatientServiceTest extends AndroidTestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

        Intent intent = new Intent(this.getContext(), PatientImpl.class);
        intent.putExtra("NAME","SSH");
        intent.putExtra("MED_ID", "Lulu");
        intent.putExtra("MED_NAME", "Vuzune");
        this.getContext().startService(intent);
    }
}
