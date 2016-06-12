package com.pharmacy.repository.patient;

import android.test.AndroidTestCase;

import com.pharmacy.domain.patient.impl.PatientImpl;
import com.pharmacy.repository.patient.impl.PatientRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by SONY on 2016-04-23.
 */
public class TestPatient extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception
    {
        PatientRepository repo = new PatientRepositoryImpl(this.getContext());
        //create
        PatientImpl createEntity = new PatientImpl.Builder()
                .bPatName("Luzo")
                .bMedID("MD52")
                .bMedName("Vunu")
                .build();
        PatientImpl insertedEntity = repo.save(createEntity);
        id = insertedEntity.getPatientID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<PatientImpl> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        PatientImpl entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        PatientImpl updateEntity = new PatientImpl.Builder()
                .copy(entity)
                .bMedID("MD71")
                .build();
        repo.update(updateEntity);
        PatientImpl newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","MD71",newEntity.getMedicalID());

        //delete entity
        repo.delete(updateEntity);
        PatientImpl deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}