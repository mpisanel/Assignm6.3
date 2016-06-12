package com.pharmacy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.pharmacy.factories.aternativecontactt.AlternativeContactTest;
import com.pharmacy.factories.aternativecontactt.AlternativeContactAddressTest;
import com.pharmacy.factories.doctort.DoctorTest;
import com.pharmacy.factories.doctort.DoctorAddressTest;
import com.pharmacy.factories.invoicet.InvoiceTest;
import com.pharmacy.factories.patientt.PatientTest;
import com.pharmacy.factories.patientt.PatientAddressTest;
import com.pharmacy.factories.pharmacyt.PharmacyTest;
import com.pharmacy.factories.pharmacyt.PharmacyAddressTest;
import com.pharmacy.factories.prescriptiont.PrescriptionTest;
/**
 * Created by SONY on 2016-04-16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AlternativeContactTest.class,
        AlternativeContactAddressTest.class,
        DoctorTest.class,
        DoctorAddressTest.class,
        InvoiceTest.class,
        PatientTest.class,
        PatientAddressTest.class,
        PharmacyTest.class,
        PharmacyAddressTest.class,
        PrescriptionTest.class})
public class AppUnitTestSuite {}
