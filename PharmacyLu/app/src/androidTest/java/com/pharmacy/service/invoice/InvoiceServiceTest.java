package com.pharmacy.service.invoice;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.pharmacy.domain.invoice.impl.InvoiceImpl;
/**
 * Created by SONY on 2016-05-08.
 */
public class InvoiceServiceTest extends AndroidTestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

        Intent intent = new Intent(this.getContext(), InvoiceImpl.class);
        intent.putExtra("DATE","11-April-2016");
        this.getContext().startService(intent);
    }
}
