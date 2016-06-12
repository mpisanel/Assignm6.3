package com.pharmacy.repository.patient;

import android.test.AndroidTestCase;

import com.pharmacy.domain.patient.impl.PatientAddressImpl;
import com.pharmacy.repository.patient.PatientAddressRepository;
import com.pharmacy.repository.patient.impl.PatientAddressRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class TestPatientAddress extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception
    {
        PatientAddressRepository repo = new PatientAddressRepositoryImpl(this.getContext());
        //create
        PatientAddressImpl createEntity = new PatientAddressImpl.Builder()
                .bStreet("Sozu")
                .bSuburb("Khayelitsha")
                .bPostCode("7754")
                .build();
        PatientAddressImpl insertedEntity = repo.save(createEntity);
        id = insertedEntity.getStreetID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<PatientAddressImpl> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        PatientAddressImpl entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        PatientAddressImpl updateEntity = new PatientAddressImpl.Builder()
                .copy(entity)
                .bSuburb("SiteD")
                .build();
        repo.update(updateEntity);
        PatientAddressImpl newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","SiteD",newEntity.getSuburb());

        //delete entity
        repo.delete(updateEntity);
        PatientAddressImpl deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
