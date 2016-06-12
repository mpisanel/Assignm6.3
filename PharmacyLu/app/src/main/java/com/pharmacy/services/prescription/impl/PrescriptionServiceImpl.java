package com.pharmacy.services.prescription.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.prescription.impl.PrescriptionImpl;
import com.pharmacy.repository.prescription.impl.PrescriptionRepositoryImpl;
import com.pharmacy.services.prescription.PrescriptionService;

/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */
public class PrescriptionServiceImpl extends IntentService implements PrescriptionService
{
    private final PrescriptionRepositoryImpl repo;

    private static final String ACTION_ADD = "com.pharmacyandroid.services.prescription.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.pharmacyandroid.services.prescription.impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.pharmacyandroid.services.prescription.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.pharmacyandroid.services.prescription.impl.extra.UPDATE";

    private static PrescriptionServiceImpl service = null;
    public static PrescriptionServiceImpl getInstance() {
        if (service == null)
            service = new PrescriptionServiceImpl();
        return service;
    }

    public PrescriptionServiceImpl()
    {
        super("PrescriptionServiceImpl");
        repo = new PrescriptionRepositoryImpl(App.getAppContext());
    }


    @Override
    public void onHandleIntent(Intent intent)
    {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PrescriptionImpl prescription = (PrescriptionImpl) intent.getSerializableExtra(EXTRA_ADD);
                postContact(prescription);
            } else if (ACTION_UPDATE.equals(action)) {
                final PrescriptionImpl prescription = (PrescriptionImpl) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(prescription);
            }
        }
    }

    private void updateContact(PrescriptionImpl prescription)
    {
        PrescriptionImpl updatedContact = repo.update(prescription);
        repo.save(updatedContact);
    }

    private void postContact(PrescriptionImpl prescription)
    {
        PrescriptionImpl createdContact = repo.update(prescription);
        repo.save(createdContact);
    }

    @Override
    public void addPrescriptionImpl(Context context, PrescriptionImpl prescription)
    {
        Intent intent = new Intent(context, PrescriptionImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, prescription);
    }

    @Override
    public void updatePrescriptionImpl(Context context, PrescriptionImpl prescription)
    {
        Intent intent = new Intent(context, PrescriptionImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, prescription);
        context.startService(intent);
    }
}
