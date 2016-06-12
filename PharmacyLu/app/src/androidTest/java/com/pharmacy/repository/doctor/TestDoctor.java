package com.pharmacy.repository.doctor;

import android.test.AndroidTestCase;

import com.pharmacy.domain.doctor.impl.DoctorImp;
import com.pharmacy.repository.doctor.impl.DoctorRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class TestDoctor extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception
    {
        DoctorRepository repo = new DoctorRepositoryImpl(this.getContext());
        //create
        DoctorImp createEntity = new DoctorImp.Builder()
                .bDocName("Luzo")
                .build();
        DoctorImp insertedEntity = repo.save(createEntity);
        id = insertedEntity.getDoctorID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<DoctorImp> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        DoctorImp entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        DoctorImp updateEntity = new DoctorImp.Builder()
                .copy(entity)
                .bDocName("Lolzo")
                .build();
        repo.update(updateEntity);
        DoctorImp newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Lolzo",newEntity.getDoctorName());

        //delete entity
        repo.delete(updateEntity);
        DoctorImp deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
