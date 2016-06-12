package com.pharmacy.service.patient;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.pharmacy.domain.patient.impl.PatientAddressImpl;
/**
 * Created by SONY on 2016-05-08.
 */
public class PatientAddressService extends AndroidTestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

        Intent intent = new Intent(this.getContext(), PatientAddressImpl.class);
        intent.putExtra("STREET","SSH");
        intent.putExtra("SUBURB", "Lulu");
        intent.putExtra("POSTCODE", "9857");
        this.getContext().startService(intent);
    }
}
