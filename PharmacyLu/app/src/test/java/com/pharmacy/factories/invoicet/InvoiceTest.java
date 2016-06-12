package com.pharmacy.factories.invoicet;
import com.pharmacy.domain.invoice.impl.InvoiceImpl;
import com.pharmacy.factory.invoice.InvoiceFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by SONY on 2016-04-16.
 */
public class InvoiceTest extends TestCase
{
    InvoiceImpl invoi;

    @Override
    public void setUp() throws Exception
    {
        invoi =  InvoiceFactory.getInvoice("Details", "14-April-2016");
    }

    public void testInvoice() throws Exception
    {
        Assert.assertEquals("Details", invoi.getDetails());
    }

    public void testUpdate() throws Exception
    {
        InvoiceImpl inImpl = new InvoiceImpl.Builder()
                .copy(invoi)
                .bCurrentDate("17-April-2016")
                .build();

        Assert.assertNotNull(inImpl);
        Assert.assertEquals("17-April-2016", inImpl.getCurrentDate());

    }
}
