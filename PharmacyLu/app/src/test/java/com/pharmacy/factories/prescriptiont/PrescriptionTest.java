package com.pharmacy.factories.prescriptiont;
import com.pharmacy.domain.prescription.impl.PrescriptionImpl;
import com.pharmacy.factory.prescription.PrescriptionFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by SONY on 2016-04-16.
 */
public class PrescriptionTest extends TestCase
{
    PrescriptionImpl presc;

    @Override
    public void setUp() throws Exception {
        presc = PrescriptionFactory.getPrescription("25-April-2016");
    }
    public void testPrescription() throws Exception
    {
        Assert.assertEquals("25-April-2016", presc.getPrescriptionDate());
    }
    public void testUpdate() throws Exception
    {
        PrescriptionImpl preUpdate = new PrescriptionImpl.Builder()
                .copy(presc)
                .bPrescriptionDate("30-April-2016")
                .build();

        Assert.assertNotNull(preUpdate);
        Assert.assertEquals(preUpdate.getPrescriptionDate(),"30-April-2016");
    }
}
