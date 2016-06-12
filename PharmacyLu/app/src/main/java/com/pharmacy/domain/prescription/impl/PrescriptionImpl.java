package com.pharmacy.domain.prescription.impl;

import com.pharmacy.domain.invoice.impl.InvoiceImpl;
import com.pharmacy.domain.prescription.Prescription;

import java.io.Serializable;

/**
 * Created by SONY on 2016-04-16.
 */
public class PrescriptionImpl implements Prescription, Serializable
{
    private Long preID;
    private String preDate;


    public PrescriptionImpl(Builder builder)
    {
        preID = builder.preID;
        preDate = builder.preDate;
    }

    public Long getPrescriptionID()
    {
        return  preID;
    }
    public String getPrescriptionDate()
    {
        return preDate;
    }

    public static class Builder
    {
        private Long preID;
        private String preDate;

        public Builder(){}
        public Builder bPreID(Long preID)
        {
            this.preID = preID;
            return this;
        }
        public Builder bPrescriptionDate(String preDate)
        {
            this.preDate = preDate;
            return this;
        }

        public  Builder copy(PrescriptionImpl pre)
        {
            this.preID = pre.getPrescriptionID();
            this.preDate = pre.getPrescriptionDate();
            return this;
        }

        public PrescriptionImpl build()
        {
            return new PrescriptionImpl(this);
        }
    }
}
