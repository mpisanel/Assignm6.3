package com.pharmacy.repository.doctor;

import android.test.AndroidTestCase;

import com.pharmacy.domain.doctor.impl.DoctorAddressImpl;
import com.pharmacy.repository.doctor.impl.DoctorAddressRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class TestDoctorAddress extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception
    {
        DoctorAddressRepository repo = new DoctorAddressRepositoryImpl(this.getContext());
        //create
        DoctorAddressImpl createEntity = new DoctorAddressImpl.Builder()
                .bStreet("Nunu")
                .bSuburb("SiteC")
                .bPostCode("5423")
                .build();
        DoctorAddressImpl insertedEntity = repo.save(createEntity);
        id = insertedEntity.getStreetID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<DoctorAddressImpl> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        DoctorAddressImpl entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        DoctorAddressImpl updateEntity = new DoctorAddressImpl.Builder()
                .copy(entity)
                .bPostCode("8884")
                .build();
        repo.update(updateEntity);
        DoctorAddressImpl newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","8884",newEntity.getPostCode());

        //delete entity
        repo.delete(updateEntity);
        DoctorAddressImpl deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
