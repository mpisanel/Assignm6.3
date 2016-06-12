package com.pharmacy.services.patient.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.patient.impl.PatientAddressImpl;
import com.pharmacy.repository.patient.impl.PatientAddressRepositoryImpl;
import com.pharmacy.services.patient.PatientAddressService;
/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */

public class PatientAddressServiceImpl extends IntentService implements PatientAddressService
{
    private final PatientAddressRepositoryImpl repo;

    private static final String ACTION_ADD = "com.pharmacyandroid.services.patient.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.pharmacyandroid.services.patient.impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.pharmacyandroid.services.patient.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.pharmacyandroid.services.patient.impl.extra.UPDATE";

    private static PatientAddressServiceImpl service = null;
    public static PatientAddressServiceImpl getInstance() {
        if (service == null)
            service = new PatientAddressServiceImpl();
        return service;
    }
    public PatientAddressServiceImpl()
    {
        super("PatientAddressServiceImpl");
        repo = new PatientAddressRepositoryImpl(App.getAppContext());
    }

    @Override
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PatientAddressImpl patientAddress = (PatientAddressImpl) intent.getSerializableExtra(EXTRA_ADD);
                postContact(patientAddress);
            } else if (ACTION_UPDATE.equals(action)) {
                final PatientAddressImpl patientAddress = (PatientAddressImpl) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(patientAddress);
            }
        }
    }

    private void updateContact(PatientAddressImpl patientAddress)
    {
        PatientAddressImpl updatedContact = repo.update(patientAddress);
        repo.save(updatedContact);
    }

    private void postContact(PatientAddressImpl patientAddress)
    {
        PatientAddressImpl createdContact = repo.update(patientAddress);
        repo.save(createdContact);
    }

    @Override
    public void addPatientAddressImpl(Context context, PatientAddressImpl patient)
    {
        Intent intent = new Intent(context, PatientAddressServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, patient);
    }

    @Override
    public void updatePatientAddressImpl(Context context, PatientAddressImpl patient)
    {
        Intent intent = new Intent(context, PatientAddressServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, patient);
        context.startService(intent);
    }
}
