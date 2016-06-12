package com.pharmacy.domain.invoice.impl;

import com.pharmacy.domain.invoice.Invoice;

import java.io.Serializable;

/**
 * Created by SONY on 2016-04-16.
 */
public class InvoiceImpl implements Invoice, Serializable
{
    private Long invoiceID;
    private String details;
    private String currentDate;

    public InvoiceImpl(Builder builder)
    {
        invoiceID = builder.invoiceID;
        details = builder.details;
        currentDate = builder.currentDate;
    }

    public Long getInvoiceID()
    {
        return invoiceID;
    }
    public String getCurrentDate()
    {
        return currentDate;
    }
    public String getDetails()
    {
        return details;
    }

    public static class Builder
    {
        private Long invoiceID;
        private String currentDate;
        private String details;

        public Builder(){}
        public Builder bInvoiceID(Long invoiceID)
        {
            this.invoiceID = invoiceID;
            return this;
        }
        public Builder bDetails(String details)
        {
            this.details = details;
            return this;
        }
        public Builder bCurrentDate(String currentDate)
        {
            this.currentDate = currentDate;
            return this;
        }
        public Builder copy(InvoiceImpl invo)
        {
            this.invoiceID = invo.invoiceID;
            this.details = invo.getDetails();
            this.currentDate = invo.getCurrentDate();
            return this;
        }
        public InvoiceImpl build()
        {
            return new InvoiceImpl(this);
        }
    }

}
