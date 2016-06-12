package com.pharmacy.factories.patientt;
import com.pharmacy.domain.patient.impl.PatientAddressImpl;
import com.pharmacy.factory.patient.PatientAddressFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by SONY on 2016-04-17.
 */
public class PatientAddressTest extends TestCase
{
    PatientAddressImpl patAddress;

    @Override
    public void setUp() throws Exception
    {
        patAddress = PatientAddressFactory.getPatientAddress("FF143", "Khayelitsha", "7788");
    }

    public void testDocAddress() throws Exception
    {
        Assert.assertEquals("FF143", patAddress.getStreet());
    }

    public void testUpdate() throws Exception
    {
        PatientAddressImpl patUp = new PatientAddressImpl.Builder()
                .copy(patAddress)
                .bPostCode("7784")
                .build();

        Assert.assertNotNull(patUp);
        Assert.assertEquals("7784", patUp.getPostCode());
    }


}
