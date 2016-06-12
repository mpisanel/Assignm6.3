package com.pharmacy.factories.doctort;
import com.pharmacy.domain.doctor.impl.DoctorImp;
import com.pharmacy.factory.doctor.DoctorFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by SONY on 2016-04-16.
 */
public class DoctorTest extends TestCase
{
    DoctorImp doct;

    @Override
    public void setUp() throws Exception
    {
        doct = DoctorFactory.getDoctor("Gugu");
    }

    public void testDoctor() throws Exception
    {
        Assert.assertEquals("Gugu", doct.getDoctorName());
        System.out.printf("Hello %s", doct.getDoctorName());
    }

    public void testUpdate() throws Exception
    {
        DoctorImp doc = new DoctorImp.Builder()
                .copy(doct)
                .bDocName("Luxolo")
                .build();

        Assert.assertNotNull(doc);
        Assert.assertEquals("Luxolo", doc.getDoctorName());
    }
}
