package com.pharmacy.factory.doctor;
import com.pharmacy.domain.doctor.impl.DoctorImp;
/**
 * Created by SONY on 2016-04-15.
 */
public class DoctorFactory
{
    public static DoctorImp getDoctor( String docName)
    {
        DoctorImp myDoc = new DoctorImp.Builder()
                .bDocName(docName)
                .build();
        return myDoc;
    }
}
