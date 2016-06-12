package com.pharmacy.service.alternativecontact;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.pharmacy.domain.alternativecontact.impl.AlternativeContactImpl;

/**
 * Created by SONY on 2016-05-08.
 */
public class AlternativeContactServiceTest extends AndroidTestCase
{
    public void setUp() throws Exception
    {
        super.setUp();

        Intent intent = new Intent(this.getContext(), AlternativeContactImpl.class);
        intent.putExtra("NAME","Kulu");
        intent.putExtra("NUMBER", "071305689");
        this.getContext().startService(intent);
    }
}
