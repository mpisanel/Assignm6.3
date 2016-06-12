package com.pharmacy.services.doctor;

import android.content.Context;

import com.pharmacy.domain.doctor.impl.DoctorImp;

/**
 * Created by SONY on 2016-05-07.
 */
public interface DoctorService
{
    void addDoctorImpl(Context context, DoctorImp doctorImp);
    void updateDoctorImpl(Context context, DoctorImp doctorImp);
}
