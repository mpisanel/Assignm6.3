package com.pharmacy.services.invoice.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.invoice.impl.InvoiceImpl;
import com.pharmacy.repository.invoice.impl.InvoiceRepositoryImpl;
import com.pharmacy.services.invoice.InvoiceService;

/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */
public class InvoiceServiceImpl extends IntentService implements InvoiceService
{
    private final InvoiceRepositoryImpl repo;

    private static final String ACTION_ADD = "com.pharmacyandroid.services.invoice.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.pharmacyandroid.services.invoice.impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.pharmacyandroid.services.invoice.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.pharmacyandroid.services.invoice.impl.extra.UPDATE";

    private static InvoiceServiceImpl service = null;
    public static InvoiceServiceImpl getInstance() {
        if (service == null)
            service = new InvoiceServiceImpl();
        return service;
    }
    public InvoiceServiceImpl()
    {
        super("InvoiceServiceImpl");
        repo = new InvoiceRepositoryImpl(App.getAppContext());
    }
    @Override
    public void onHandleIntent(Intent intent)
    {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final InvoiceImpl invoice = (InvoiceImpl) intent.getSerializableExtra(EXTRA_ADD);
                postContact(invoice);
            } else if (ACTION_UPDATE.equals(action)) {
                final InvoiceImpl invoice = (InvoiceImpl) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(invoice);
            }
        }
    }

    private void updateContact(InvoiceImpl invoice)
    {
        InvoiceImpl updatedContact = repo.update(invoice);
        repo.save(updatedContact);
    }

    private void postContact(InvoiceImpl invoice)
    {
        InvoiceImpl createdContact = repo.update(invoice);
        repo.save(createdContact);
    }


    @Override
    public void addInvoiceImpl(Context context, InvoiceImpl invoice)
    {
        Intent intent = new Intent(context, InvoiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, invoice);
    }

    @Override
    public void updateInvoiceImpl(Context context, InvoiceImpl invoice)
    {
        Intent intent = new Intent(context, InvoiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, invoice);
        context.startService(intent);
    }
}
