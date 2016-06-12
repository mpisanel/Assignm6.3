package com.pharmacy.repository.patient;

import com.pharmacy.domain.patient.Patient;
import com.pharmacy.domain.patient.impl.PatientImpl;
import com.pharmacy.repository.settings.Repository;

/**
 * Created by SONY on 2016-04-21.
 */
public interface PatientRepository extends Repository<PatientImpl,Long> {
}
