package com.pharmacy.services.doctor;

import android.content.Context;

import com.pharmacy.domain.doctor.impl.DoctorAddressImpl;

/**
 * Created by SONY on 2016-05-07.
 */
public interface DoctorAddressService
{
    void addDoctorAddressImpl(Context context, DoctorAddressImpl doctorAddress);
    void updateDoctorImpl(Context context, DoctorAddressImpl doctorAddress);
}
