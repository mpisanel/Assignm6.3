package com.pharmacy.services.alternativecontact;

import android.content.Context;

import com.pharmacy.domain.alternativecontact.impl.AlternativeContactAddressImpl;

/**
 * Created by SONY on 2016-05-07.
 */
public interface AlternativeContactAddressService
{
    void addAlternativeContactAddressImpl(Context context, AlternativeContactAddressImpl patient);
    void updateAlternativeContactAddressImpl(Context context, AlternativeContactAddressImpl patient);
}
