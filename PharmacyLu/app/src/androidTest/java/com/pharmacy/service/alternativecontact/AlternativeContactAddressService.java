package com.pharmacy.service.alternativecontact;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.pharmacy.domain.alternativecontact.impl.AlternativeContactAddressImpl;

/**
 * Created by SONY on 2016-05-08.
 */
public class AlternativeContactAddressService extends AndroidTestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

        Intent intent = new Intent(this.getContext(), AlternativeContactAddressImpl.class);
        intent.putExtra("STREET","ff142");
        intent.putExtra("SUBURB", "Zola");
        intent.putExtra("POSTCODE", "7854");
        this.getContext().startService(intent);
    }
}
