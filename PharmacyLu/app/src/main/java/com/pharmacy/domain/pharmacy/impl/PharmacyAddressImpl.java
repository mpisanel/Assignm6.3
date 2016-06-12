package com.pharmacy.domain.pharmacy.impl;

import com.pharmacy.domain.pharmacy.PharmacyAddress;

import java.io.Serializable;

/**
 * Created by SONY on 2016-04-16.
 */
public class PharmacyAddressImpl implements PharmacyAddress, Serializable
{
    private String street;
    private String suburb;
    private String postCode;
    public Long streetID;

    public String getPostCode()
    {
        return postCode;
    }
    public String getSuburb()
    {
        return suburb;
    }
    public String getStreet()
    {
        return street;
    }
    public Long getStreetID() {
        return streetID;
    }

    public static class Builder
    {
        private String street;
        private String suburb;
        private String postCode;
        public Long streetID;

        public Builder(){}
        public Builder bStreetID(Long streetID)
        {
            this.streetID = streetID;
            return this;
        }
        public Builder bStreet(String street)
        {
            this.street = street;
            return this;
        }
        public Builder bSuburb(String suburb)
        {
            this.suburb = suburb;
            return this;
        }
        public Builder bPostCode(String postCode)
        {
            this.postCode = postCode;
            return this;
        }
        public Builder copy(PharmacyAddressImpl parmAddress)
        {
            this.streetID = parmAddress.getStreetID();
            this.street = parmAddress.getStreet();
            this.suburb = parmAddress.getSuburb();
            this.postCode = parmAddress.getPostCode();
            return this;
        }
        public PharmacyAddressImpl build()
        {
            return  new PharmacyAddressImpl(this);
        }
    }
    public PharmacyAddressImpl(Builder build)
    {
        streetID = build.streetID;
        street = build.street;
        suburb = build.suburb;
        postCode = build.postCode;
    }
}
