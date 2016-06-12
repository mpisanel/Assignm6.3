package com.pharmacy.factories.aternativecontactt;
import com.pharmacy.domain.alternativecontact.impl.AlternativeContactAddressImpl;
import com.pharmacy.factory.alternativecontact.AlternativeContactAddressFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by SONY on 2016-04-17.
 */
public class AlternativeContactAddressTest extends TestCase
{
    AlternativeContactAddressImpl alterContAddress;

    @Override
    public void setUp() throws Exception
    {
        alterContAddress = AlternativeContactAddressFactory.getAlternativeAddress("FF142", "Khayelitsha", "7788");
    }

    public void testAlternativeConAddress() throws Exception
    {
        Assert.assertEquals("FF142", alterContAddress.getStreet());
    }

    public void testUpdate() throws Exception
    {
        AlternativeContactAddressImpl altAddUp = new AlternativeContactAddressImpl.Builder()
                .copy(alterContAddress)
                .bPostCode("7784")
                .build();

        Assert.assertNotNull(altAddUp);
        Assert.assertEquals("7784", altAddUp.getPostCode());
    }
}
