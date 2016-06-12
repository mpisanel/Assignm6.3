package com.pharmacy.repository.invoice;

import android.test.AndroidTestCase;

import com.pharmacy.domain.invoice.impl.InvoiceImpl;
import com.pharmacy.repository.invoice.impl.InvoiceRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by SONY on 2016-04-24.
 */
public class TestInvoice extends AndroidTestCase
{
    private static final String TAG = "SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception
    {
        InvoiceRepository repo = new InvoiceRepositoryImpl(this.getContext());
        //create
        InvoiceImpl createEntity = new InvoiceImpl.Builder()
                .bCurrentDate("24-April-2016")
                .bDetails("Lost in Android")
                .build();
        InvoiceImpl insertedEntity = repo.save(createEntity);
        id = insertedEntity.getInvoiceID();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //read all
        Set<InvoiceImpl> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //read entity
        InvoiceImpl entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //update entity
        InvoiceImpl updateEntity = new InvoiceImpl.Builder()
                .copy(entity)
                .bDetails("Pay")
                .build();
        repo.update(updateEntity);
        InvoiceImpl newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Pay",newEntity.getDetails());

        //delete entity
        repo.delete(updateEntity);
        InvoiceImpl deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
