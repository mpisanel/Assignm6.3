package com.pharmacy.domain.doctor.impl;

import com.pharmacy.domain.doctor.Doctor;

import java.io.Serializable;

/**
 * Created by SONY on 2016-04-15.
 */
public class DoctorImp implements Doctor, Serializable
{
    private Long doctorID;
    private String doctorName;

    @Override
    public Long getDoctorID()
    {
        return doctorID;
    }
    public String getDoctorName()
    {
        return doctorName;
    }

    public static class Builder
    {
        private Long doctorID;
        private String doctorName;

        public Builder(){}
        public Builder bDoctorID(Long doctorID)
        {
            this.doctorID = doctorID;
            return this;
        }
        public Builder bDocName(String doctorName)
        {
            this.doctorName = doctorName;
            return this;
        }
        public Builder copy(DoctorImp doc)
        {
            this.doctorID = doc.getDoctorID();
            this.doctorName = doc.getDoctorName();
            return this;
        }
        public DoctorImp build()
        {
            return new DoctorImp(this);
        }
    }

    public DoctorImp(Builder build)
    {
        doctorID = build.doctorID;
        doctorName = build.doctorName;
    }
    public String toString()
    {
        return String.format("The patient Information\n------------------------------------\n %s\n\n",doctorName );
    }
}
