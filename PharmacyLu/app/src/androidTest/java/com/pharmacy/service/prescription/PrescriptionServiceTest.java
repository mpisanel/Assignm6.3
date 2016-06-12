package com.pharmacy.service.prescription;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.pharmacy.domain.prescription.impl.PrescriptionImpl;

/**
 * Created by SONY on 2016-05-08.
 */
public class PrescriptionServiceTest extends AndroidTestCase
{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), PrescriptionImpl.class);
        intent.putExtra("DATE","14-April-2016");
        this.getContext().startService(intent);

    }
}
