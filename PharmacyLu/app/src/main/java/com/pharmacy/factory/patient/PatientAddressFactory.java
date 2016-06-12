package com.pharmacy.factory.patient;
import com.pharmacy.domain.patient.impl.PatientAddressImpl;
/**
 * Created by SONY on 2016-04-16.
 */
public class PatientAddressFactory
{
    public static PatientAddressImpl getPatientAddress(String pStreet, String pSuburb, String pPostCode)
    {
        PatientAddressImpl myPatAddress = new PatientAddressImpl.Builder()
                .bStreet(pStreet)
                .bSuburb(pSuburb)
                .bPostCode(pPostCode)
                .build();
        return myPatAddress;
    }
}
