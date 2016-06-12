package com.pharmacy.services.alternativecontact;

import android.content.Context;

import com.pharmacy.domain.alternativecontact.impl.AlternativeContactImpl;

/**
 * Created by SONY on 2016-05-07.
 */
public interface AlternativeContactService
{
    void addAlternativeContactImpl(Context context, AlternativeContactImpl patient);
    void updateAlternativeContactImpl(Context context, AlternativeContactImpl patient);
}
