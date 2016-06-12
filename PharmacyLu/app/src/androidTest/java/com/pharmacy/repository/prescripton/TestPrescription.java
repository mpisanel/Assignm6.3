package com.pharmacy.repository.prescripton;

import android.test.AndroidTestCase;
import com.pharmacy.domain.prescription.impl.PrescriptionImpl;
import com.pharmacy.repository.prescription.impl.PrescriptionRepositoryImpl;
import com.pharmacy.repository.prescription.PrescriptionRepository;

import junit.framework.Assert;
import java.util.Set;

/**
 * Created by SONY on 2016-04-24.
 */
public class TestPrescription extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception
    {
        PrescriptionRepository repo = new PrescriptionRepositoryImpl(this.getContext());
        //create
        PrescriptionImpl createEntity = new PrescriptionImpl.Builder()
                .bPrescriptionDate("24-April-2016")
                .build();
        PrescriptionImpl insertedEntity = repo.save(createEntity);
        id = insertedEntity.getPrescriptionID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<PrescriptionImpl> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        PrescriptionImpl entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        PrescriptionImpl updateEntity = new PrescriptionImpl.Builder()
                .copy(entity)
                .bPrescriptionDate("25-April-2016")
                .build();
        repo.update(updateEntity);
        PrescriptionImpl newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","25-April-2016",newEntity.getPrescriptionDate());

        //delete entity
        repo.delete(updateEntity);
        PrescriptionImpl deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
