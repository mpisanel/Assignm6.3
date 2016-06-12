package com.pharmacy.services.alternativecontact.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.alternativecontact.impl.AlternativeContactImpl;
import com.pharmacy.repository.alternativecontact.impl.AlternativeContactRepositoryImpl;
import com.pharmacy.services.alternativecontact.AlternativeContactService;

/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */
public class AlternativeContactServiceImpl extends IntentService implements AlternativeContactService
{
    private final AlternativeContactRepositoryImpl repo;

    public static final String ACTION_ADD = "com.pharmacyandroid.services.alternativecontact.impl.action.ADD";
    public static final String ACTION_UPDATE = "com.pharmacyandroid.services.alternativecontact.impl.action.UPDATE";

    public static final String EXTRA_ADD = "com.pharmacyandroid.services.alternativecontact.impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.pharmacyandroid.services.alternativecontact.impl.extra.UPDATE";

    private static AlternativeContactServiceImpl service = null;
    public static AlternativeContactServiceImpl getInstance() {
        if (service == null)
            service = new AlternativeContactServiceImpl();
        return service;
    }

    public AlternativeContactServiceImpl()
    {
        super("AlternativeContactServiceImpl");
        repo = new AlternativeContactRepositoryImpl(App.getAppContext());
    }

    @Override
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final AlternativeContactImpl alternativeContact = (AlternativeContactImpl) intent.getSerializableExtra(EXTRA_ADD);
                postContact(alternativeContact);
            } else if (ACTION_UPDATE.equals(action)) {
                final AlternativeContactImpl alternativeContact = (AlternativeContactImpl) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(alternativeContact);
            }
        }
    }

    private void updateContact(AlternativeContactImpl alternativeContact)
    {
        AlternativeContactImpl updatedContact = repo.update(alternativeContact);
        repo.save(updatedContact);
    }

    private void postContact(AlternativeContactImpl alternativeContact)
    {
        AlternativeContactImpl createdContact = repo.update(alternativeContact);
        repo.save(createdContact);
    }

    @Override
    public void addAlternativeContactImpl(Context context, AlternativeContactImpl patient)
    {
        Intent intent = new Intent(context, AlternativeContactServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, patient);
    }

    @Override
    public void updateAlternativeContactImpl(Context context, AlternativeContactImpl patient)
    {
        Intent intent = new Intent(context, AlternativeContactServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, patient);
        context.startService(intent);
    }
}
