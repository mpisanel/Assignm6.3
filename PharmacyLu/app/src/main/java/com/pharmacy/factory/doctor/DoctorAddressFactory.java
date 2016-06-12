package com.pharmacy.factory.doctor;
import com.pharmacy.domain.doctor.impl.DoctorAddressImpl;
/**
 * Created by SONY on 2016-04-16.
 */
public class DoctorAddressFactory
{
    public static DoctorAddressImpl getDoctorAddress(String dStreet, String dSuburb, String dPostCode)
    {
        DoctorAddressImpl myDocAddress = new DoctorAddressImpl.Builder()
                .bStreet(dStreet)
                .bSuburb(dSuburb)
                .bPostCode(dPostCode)
                .build();
        return myDocAddress;
    }
}
