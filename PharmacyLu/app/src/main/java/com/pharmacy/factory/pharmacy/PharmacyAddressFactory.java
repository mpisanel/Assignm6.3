package com.pharmacy.factory.pharmacy;
import com.pharmacy.domain.pharmacy.impl.PharmacyAddressImpl;
/**
 * Created by SONY on 2016-04-16.
 */
public class PharmacyAddressFactory
{
    public static PharmacyAddressImpl getPatientAddress(String pharmStreet, String pharmSuburb, String pharmPostCode)
    {
        PharmacyAddressImpl myParmAddress = new PharmacyAddressImpl.Builder()
                .bStreet(pharmStreet)
                .bSuburb(pharmSuburb)
                .bPostCode(pharmPostCode)
                .build();
        return myParmAddress;
    }
}
