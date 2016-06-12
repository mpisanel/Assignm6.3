package com.pharmacy.repository.pharmacy;

import android.test.AndroidTestCase;

import com.pharmacy.domain.pharmacy.impl.PharmacyAddressImpl;
import com.pharmacy.repository.pharmacy.impl.PharmacyAddressRepositoryImpl;

import junit.framework.Assert;
import java.util.Set;

/**
 * Created by SONY on 2016-04-24.
 */
public class TestPharmacyAddress extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        PharmacyAddressRepository repo = new PharmacyAddressRepositoryImpl(this.getContext());
        //create
        PharmacyAddressImpl createEntity = new PharmacyAddressImpl.Builder()
                .bStreet("Nonzi")
                .bSuburb("Khayelitsha")
                .bPostCode("7754")
                .build();
        PharmacyAddressImpl insertedEntity = repo.save(createEntity);
        id = insertedEntity.getStreetID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<PharmacyAddressImpl> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        PharmacyAddressImpl entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        PharmacyAddressImpl updateEntity = new PharmacyAddressImpl.Builder()
                .copy(entity)
                .bSuburb("SiteB")
                .build();
        repo.update(updateEntity);
        PharmacyAddressImpl newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","SiteB",newEntity.getSuburb());

        //delete entity
        repo.delete(updateEntity);
        PharmacyAddressImpl deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
