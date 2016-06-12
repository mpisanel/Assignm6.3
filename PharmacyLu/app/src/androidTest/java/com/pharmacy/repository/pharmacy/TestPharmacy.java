package com.pharmacy.repository.pharmacy;

import android.test.AndroidTestCase;

import com.pharmacy.domain.pharmacy.impl.PharmacyImpl;
import com.pharmacy.repository.pharmacy.impl.PharmacyRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by SONY on 2016-04-24.
 */
public class TestPharmacy extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception
    {
        PharmacyRepository repo = new PharmacyRepositoryImpl(this.getContext());
        //create
        PharmacyImpl createEntity = new PharmacyImpl.Builder()
                .bPharmName("Juju")
                .build();
        PharmacyImpl insertedEntity = repo.save(createEntity);
        id = insertedEntity.getPharmacyID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<PharmacyImpl> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        PharmacyImpl entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        PharmacyImpl updateEntity = new PharmacyImpl.Builder()
                .copy(entity)
                .bPharmName("Tutu")
                .build();
        repo.update(updateEntity);
        PharmacyImpl newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Tutu",newEntity.getPharmacyName());

        //delete entity
        repo.delete(updateEntity);
        PharmacyImpl deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
