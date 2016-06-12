package com.pharmacy.domain.doctor.impl;

import com.pharmacy.domain.doctor.DoctorAddress;

import java.io.Serializable;

/**
 * Created by SONY on 2016-04-16.
 */
public class DoctorAddressImpl implements DoctorAddress, Serializable
{
    private String street;
    private String suburb;
    private String postCode;
    private Long streetID;

    public Long getStreetID() {
        return streetID;
    }
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

    public static class Builder
    {
        private String street;
        private String suburb;
        private String postCode;
        private Long streetID;

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

        public Builder copy(DoctorAddressImpl docAddress)
        {
            this.streetID = docAddress.getStreetID();
            this.street = docAddress.getStreet();
            this.suburb = docAddress.getSuburb();
            this.postCode = docAddress.getPostCode();
            return this;
        }
        public DoctorAddressImpl build()
        {
            return  new DoctorAddressImpl(this);
        }
    }
    public DoctorAddressImpl(Builder build)
    {
        streetID = build.streetID;
        street = build.street;
        suburb = build.suburb;
        postCode = build.postCode;
    }
}
