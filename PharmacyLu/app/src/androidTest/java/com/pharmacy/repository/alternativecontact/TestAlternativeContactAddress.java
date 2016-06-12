package com.pharmacy.repository.alternativecontact;

import android.test.AndroidTestCase;

import com.pharmacy.domain.alternativecontact.impl.AlternativeContactAddressImpl;
import com.pharmacy.repository.alternativecontact.impl.AlternativeContactAddressRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by SONY on 2016-04-24.
 */
public class TestAlternativeContactAddress extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception
    {
        AlternativeContactAddressRepository repo = new AlternativeContactAddressRepositoryImpl(this.getContext());
        //create
        AlternativeContactAddressImpl createEntity = new AlternativeContactAddressImpl.Builder()
                .bStreet("Wunu")
                .bSuburb("Khayelitsha")
                .bPostCode("7758")
                .build();
        AlternativeContactAddressImpl insertedEntity = repo.save(createEntity);
        id = insertedEntity.getStreetID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<AlternativeContactAddressImpl> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        AlternativeContactAddressImpl entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        AlternativeContactAddressImpl updateEntity = new AlternativeContactAddressImpl.Builder()
                .copy(entity)
                .bSuburb("SiteB")
                .build();
        repo.update(updateEntity);
        AlternativeContactAddressImpl newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","SiteB",newEntity.getSuburb());

        //delete entity
        repo.delete(updateEntity);
        AlternativeContactAddressImpl deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
