package com.pharmacy.domain.patient.impl;

import com.pharmacy.domain.patient.Patient;

import java.io.Serializable;

/**
 * Created by SONY on 2016-04-15.
 */
public class PatientImpl implements Patient, Serializable
{
    private Long patientID;
    private String patientName;
    private String medicalID;
    private String medicalName;

    @Override
    public Long getPatientID()
    {
        return patientID;
    }
    public String getPatientName()
    {
        return patientName;
    }
    public String getMedicalID()
    {
        return medicalID;
    }
    public String getMedicalName()
    {
        return medicalName;
    }

    public static class Builder
    {
        private Long patientID;
        private String patientName;
        private String medicalID;
        private String medicalName;

        public  Builder(){}

        public Builder bPatName(String patientName)
        {
            this.patientName = patientName;
            return this;
        }
        public Builder bPatID(Long patientID)
        {
            this.patientID = patientID;
            return  this;
        }
        public Builder bMedID(String medicalID)
        {
            this.medicalID = medicalID;
            return this;
        }
        public Builder bMedName(String medicalName)
        {
            this.medicalName = medicalName;
            return this;
        }

        public Builder copy(PatientImpl pat)
        {
            this.patientID = pat.getPatientID();
            this.medicalName = pat.getMedicalName();
            this.medicalID = pat.getMedicalID();
            this.medicalName = pat.getMedicalName();

            return this;
        }
        public PatientImpl build()
        {
            return  new PatientImpl(this);
        }
    }
    public PatientImpl(Builder build)
    {
        patientID = build.patientID;
        patientName = build.patientName;
        medicalID = build.medicalID;
        medicalName = build.medicalName;
    }
}
