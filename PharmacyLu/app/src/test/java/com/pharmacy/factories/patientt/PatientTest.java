package com.pharmacy.factories.patientt;
import com.pharmacy.domain.patient.impl.PatientImpl;
import com.pharmacy.factory.patient.PatientFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by SONY on 2016-04-16.
 */
public class PatientTest extends TestCase
{
    PatientImpl pati;

    @Override
    public void setUp() throws Exception
    {
        pati = PatientFactory.getPatient("Lulu", "MED9", "Venzi");
    }
    public void testPatient() throws Exception
    {
        Assert.assertEquals("MED9", pati.getMedicalID());
    }

    public void testUpdate() throws Exception
    {
        PatientImpl patUpdate = new PatientImpl.Builder()
                .copy(pati)
                .bMedName("Zulu")
                .build();

        Assert.assertNotNull(patUpdate);
        Assert.assertEquals(patUpdate.getMedicalName(),"Zulu");
    }
}
