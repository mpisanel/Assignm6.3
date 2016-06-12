package com.pharmacy.factories.pharmacyt;
import com.pharmacy.domain.pharmacy.impl.PharmacyImpl;
import com.pharmacy.factory.pharmacy.PharmacyFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by SONY on 2016-04-16.
 */
public class PharmacyTest extends TestCase
{
    PharmacyImpl phar;

    @Override
    public void setUp() throws Exception
    {
        phar = PharmacyFactory.getPharmacy("Zizo");
    }

    public void testPharmacy() throws Exception
    {
        Assert.assertEquals("Zizo", phar.getPharmacyName());
    }

    public void testUpdate() throws Exception
    {
        PharmacyImpl pha = new PharmacyImpl.Builder()
                .copy(phar)
                .bPharmName("Mpisane")
                .build();

        Assert.assertNotNull(pha);
        Assert.assertEquals("Mpisane", pha.getPharmacyName());
    }
}
