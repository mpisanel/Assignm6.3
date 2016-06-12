package com.pharmacy.domain.pharmacy.impl;

import com.pharmacy.domain.pharmacy.Pharmacy;

import java.io.Serializable;

/**
 * Created by SONY on 2016-04-16.
 */
public class PharmacyImpl implements Pharmacy, Serializable
{
    private Long pharmacyID;
    private String pharmacyName;

    public Long getPharmacyID()
    {
        return pharmacyID;
    }
    public String getPharmacyName()
    {
        return pharmacyName;
    }

    public static class Builder
    {
        private Long pharmacyID;
        private String pharmacyName;

        public Builder(){}
        public Builder bPharmacyID(Long pharmacyID)
        {
            this.pharmacyID = pharmacyID;
            return this;
        }
        public Builder bPharmName(String pharmacyName)
        {
            this.pharmacyName = pharmacyName;
            return this;
        }

        public Builder copy(PharmacyImpl pharm)
        {
            this.pharmacyID = pharm.getPharmacyID();
            this.pharmacyName = pharm.getPharmacyName();
            return this;
        }
        public PharmacyImpl build()
        {
            return new PharmacyImpl(this);
        }
    }

    public PharmacyImpl(Builder build)
    {
        pharmacyID = build.pharmacyID;
        pharmacyName = build.pharmacyName;
    }

}
