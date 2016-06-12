package com.pharmacy.service.pharmacy;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.pharmacy.domain.pharmacy.impl.PharmacyImpl;

/**
 * Created by SONY on 2016-05-08.
 */
public class PharmacyServiceTest extends AndroidTestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

        Intent intent = new Intent(this.getContext(), PharmacyImpl.class);
        intent.putExtra("NAME","Alu");
        this.getContext().startService(intent);
    }
}
