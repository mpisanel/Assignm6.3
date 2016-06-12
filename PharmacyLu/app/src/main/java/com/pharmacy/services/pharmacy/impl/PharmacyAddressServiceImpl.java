package com.pharmacy.services.pharmacy.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.pharmacy.impl.PharmacyAddressImpl;
import com.pharmacy.repository.pharmacy.impl.PharmacyAddressRepositoryImpl;
import com.pharmacy.services.pharmacy.PharmacyAddressService;

/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */
public class PharmacyAddressServiceImpl extends IntentService implements PharmacyAddressService
{
    private final PharmacyAddressRepositoryImpl repo;

    private static final String ACTION_ADD = "com.pharmacyandroid.services.pharmacy.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.pharmacyandroid.services.pharmacy.impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.pharmacyandroid.services.pharmacy.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.pharmacyandroid.services.pharmacy.impl.extra.UPDATE";

    private static PharmacyAddressServiceImpl service = null;
    public static PharmacyAddressServiceImpl getInstance() {
        if (service == null)
            service = new PharmacyAddressServiceImpl();
        return service;
    }

    public PharmacyAddressServiceImpl()
    {
        super("PharmacyAddressServiceImpl");
        repo = new PharmacyAddressRepositoryImpl(App.getAppContext());
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PharmacyAddressImpl pharmacyAddress = (PharmacyAddressImpl) intent.getSerializableExtra(EXTRA_ADD);
                postContact(pharmacyAddress);
            } else if (ACTION_UPDATE.equals(action)) {
                final PharmacyAddressImpl pharmacyAddress = (PharmacyAddressImpl) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(pharmacyAddress);
            }
        }
    }

    private void updateContact(PharmacyAddressImpl pharmacyAddress)
    {
        PharmacyAddressImpl updatedContact = repo.update(pharmacyAddress);
        repo.save(updatedContact);
    }

    private void postContact(PharmacyAddressImpl pharmacyAddress)
    {
        PharmacyAddressImpl createdContact = repo.update(pharmacyAddress);
        repo.save(createdContact);
    }

    @Override
    public void addPharmacyAddressImpl(Context context, PharmacyAddressImpl pharmacyAddress)
    {
        Intent intent = new Intent(context, PharmacyAddressImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, pharmacyAddress);
    }

    @Override
    public void updatePharmacyAddressImpl(Context context, PharmacyAddressImpl pharmacyAddress)
    {
        Intent intent = new Intent(context, PharmacyAddressImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, pharmacyAddress);
        context.startService(intent);
    }
}
