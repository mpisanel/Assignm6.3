package com.pharmacy.domain.alternativecontact.impl;

import com.pharmacy.domain.alternativecontact.AlternativeContact;

import java.io.Serializable;

/**
 * Created by SONY on 2016-04-16.
 */
public class AlternativeContactImpl implements AlternativeContact, Serializable
{
    private Long alternID;
    private String alternNumber;
    private String alternName;

    public String getAlternativeContactNumber()
    {
        return alternNumber;
    }
    public String getAlternName()
    {
        return alternName;
    }
    public Long getAlternID() {
        return alternID;
    }

    public static class Builder
    {
        private String alternNumber;
        private String alternName;
        private Long alternID;

        public Builder(){}
        public Builder bAleternID(Long alternID)
        {
            this.alternID = alternID;
            return this;
        }
        public Builder bAlternNumber(String alternNumber)
        {
            this.alternNumber = alternNumber;
            return this;
        }

        public Builder bAlternName(String alternName)
        {
            this.alternName = alternName;
            return this;
        }

        public Builder copy(AlternativeContactImpl altern)
        {
            this.alternID = altern.getAlternID();
            this.alternNumber = altern.getAlternativeContactNumber();
            this.alternName = altern.getAlternName();
            return this;
        }
        public AlternativeContactImpl build()
        {
            return new AlternativeContactImpl(this);
        }
    }
    public AlternativeContactImpl(Builder build)
    {
        alternID = build.alternID;
        alternNumber = build.alternNumber;
        alternName = build.alternName;
    }
}
