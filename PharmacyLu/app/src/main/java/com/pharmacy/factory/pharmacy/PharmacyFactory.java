package com.pharmacy.factory.pharmacy;
import com.pharmacy.domain.pharmacy.impl.PharmacyImpl;
/**
 * Created by SONY on 2016-04-16.
 */
public class PharmacyFactory
{
    public static PharmacyImpl getPharmacy(String pharmName)
    {
        PharmacyImpl myPharm = new PharmacyImpl.Builder()
                .bPharmName(pharmName)
                .build();
        return myPharm;
    }
}
