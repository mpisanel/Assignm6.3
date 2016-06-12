package com.pharmacy.service.pharmacy;

import android.content.Intent;
import android.test.AndroidTestCase;
import com.pharmacy.domain.pharmacy.impl.PharmacyAddressImpl;
/**
 * Created by SONY on 2016-05-08.
 */
public class PharmacyAddressServiceTest extends AndroidTestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

        Intent intent = new Intent(this.getContext(), PharmacyAddressImpl.class);
        intent.putExtra("STREET","KLZ 785");
        intent.putExtra("SUBURB", "Manda");
        intent.putExtra("POSTCODE", "6666");
        this.getContext().startService(intent);
    }
}
