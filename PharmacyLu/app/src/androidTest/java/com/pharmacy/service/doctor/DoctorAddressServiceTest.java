package com.pharmacy.service.doctor;

import android.content.Intent;
import android.test.AndroidTestCase;
import com.pharmacy.domain.doctor.impl.DoctorAddressImpl;
/**
 * Created by SONY on 2016-05-08.
 */
public class DoctorAddressServiceTest extends AndroidTestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

        Intent intent = new Intent(this.getContext(), DoctorAddressImpl.class);
        intent.putExtra("STREET","XO78");
        intent.putExtra("SUBURB", "Khaya");
        intent.putExtra("POSTCODE", "967");
        this.getContext().startService(intent);
    }

}
