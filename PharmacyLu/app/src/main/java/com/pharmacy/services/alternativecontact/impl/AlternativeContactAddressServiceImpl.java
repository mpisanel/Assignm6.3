package com.pharmacy.services.alternativecontact.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.alternativecontact.impl.AlternativeContactAddressImpl;
import com.pharmacy.repository.alternativecontact.impl.AlternativeContactAddressRepositoryImpl;
import com.pharmacy.services.alternativecontact.AlternativeContactAddressService;
/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */

public class AlternativeContactAddressServiceImpl extends IntentService implements AlternativeContactAddressService
{
    private final AlternativeContactAddressRepositoryImpl repo;
    private static final String ACTION_ADD = "com.pharmacyandroid.services.alternativecontact.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.pharmacyandroid.services.alternativecontact.impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.pharmacyandroid.services.alternativecontact.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.pharmacyandroid.services.alternativecontact.impl.extra.UPDATE";

    private static AlternativeContactAddressServiceImpl service = null;
    public static AlternativeContactAddressServiceImpl getInstance() {
        if (service == null)
            service = new AlternativeContactAddressServiceImpl();
        return service;
    }
    public AlternativeContactAddressServiceImpl()
    {
        super("AlternativeContactAddressServiceImpl");
        repo = new AlternativeContactAddressRepositoryImpl(App.getAppContext());
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final AlternativeContactAddressImpl alternativeContactAddress = (AlternativeContactAddressImpl) intent.getSerializableExtra(EXTRA_ADD);
                postContact(alternativeContactAddress);
            } else if (ACTION_UPDATE.equals(action)) {
                final AlternativeContactAddressImpl alternativeContactAddress = (AlternativeContactAddressImpl) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(alternativeContactAddress);
            }
        }
    }

    private void updateContact(AlternativeContactAddressImpl alternativeContactAddress)
    {
        AlternativeContactAddressImpl updatedContact = repo.update(alternativeContactAddress);
        repo.save(updatedContact);
    }

    private void postContact(AlternativeContactAddressImpl alternativeContactAddress)
    {
        AlternativeContactAddressImpl createdContact = repo.update(alternativeContactAddress);
        repo.save(createdContact);
    }

    @Override
    public void addAlternativeContactAddressImpl(Context context, AlternativeContactAddressImpl patient)
    {
        Intent intent = new Intent(context, AlternativeContactAddressServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, patient);
    }

    @Override
    public void updateAlternativeContactAddressImpl(Context context, AlternativeContactAddressImpl patient)
    {
        Intent intent = new Intent(context, AlternativeContactAddressServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, patient);
        context.startService(intent);
    }

    private void deleteAddressRecords()
    {
        repo.deleteAll();
    }


}
