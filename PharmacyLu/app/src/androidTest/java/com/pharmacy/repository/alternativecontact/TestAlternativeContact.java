package com.pharmacy.repository.alternativecontact;

import android.test.AndroidTestCase;

import com.pharmacy.domain.alternativecontact.impl.AlternativeContactImpl;
import com.pharmacy.repository.alternativecontact.impl.AlternativeContactRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by SONY on 2016-04-24.
 */
public class TestAlternativeContact extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception
    {
        AlternativeContactRepository repo = new AlternativeContactRepositoryImpl(this.getContext());
        //create
        AlternativeContactImpl createEntity = new AlternativeContactImpl.Builder()
                .bAlternName("Guzu")
                .bAlternNumber("017452853")
                .build();
        AlternativeContactImpl insertedEntity = repo.save(createEntity);
        id = insertedEntity.getAlternID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<AlternativeContactImpl> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        AlternativeContactImpl entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        AlternativeContactImpl updateEntity = new AlternativeContactImpl.Builder()
                .copy(entity)
                .bAlternNumber("081365284")
                .build();
        repo.update(updateEntity);
        AlternativeContactImpl newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","081365284",newEntity.getAlternativeContactNumber());

        //delete entity
        repo.delete(updateEntity);
        AlternativeContactImpl deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
