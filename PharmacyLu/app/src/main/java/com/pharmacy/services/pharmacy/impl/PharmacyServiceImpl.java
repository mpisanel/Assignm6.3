package com.pharmacy.services.pharmacy.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.pharmacy.impl.PharmacyImpl;
import com.pharmacy.repository.pharmacy.impl.PharmacyRepositoryImpl;
import com.pharmacy.services.pharmacy.PharmacyService;

/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */
public class PharmacyServiceImpl extends IntentService implements PharmacyService
{
    private final PharmacyRepositoryImpl repo;

    private static final String ACTION_ADD = "com.pharmacyandroid.services.pharmacy.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.pharmacyandroid.services.pharmacy.impl.action.UPDATE";


    private static final String EXTRA_ADD = "com.pharmacyandroid.services.pharmacy.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.pharmacyandroid.services.pharmacy.impl.extra.UPDATE";

    private static PharmacyServiceImpl service = null;
    public static PharmacyServiceImpl getInstance() {
        if (service == null)
            service = new PharmacyServiceImpl();
        return service;
    }
    public PharmacyServiceImpl()
    {
        super("PharmacyServiceImpl");
        repo = new PharmacyRepositoryImpl(App.getAppContext());
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PharmacyImpl pharmacy = (PharmacyImpl) intent.getSerializableExtra(EXTRA_ADD);
                postContact(pharmacy);
            } else if (ACTION_UPDATE.equals(action)) {
                final PharmacyImpl pharmacy = (PharmacyImpl) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(pharmacy);
            }
        }
    }

    private void updateContact(PharmacyImpl pharmacy)
    {
        PharmacyImpl updatedContact = repo.update(pharmacy);
        repo.save(updatedContact);
    }

    private void postContact(PharmacyImpl pharmacy)
    {
        PharmacyImpl createdContact = repo.update(pharmacy);
        repo.save(createdContact);
    }

    @Override
    public void addPharmacyImpl(Context context, PharmacyImpl pharmacy)
    {
        Intent intent = new Intent(context, PharmacyImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, pharmacy);
    }

    @Override
    public void updatePharmacyImpl(Context context, PharmacyImpl pharmacy)
    {
        Intent intent = new Intent(context, PharmacyImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, pharmacy);
        context.startService(intent);
    }
}
