package com.pharmacy.services.patient.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.patient.impl.PatientImpl;
import com.pharmacy.repository.patient.PatientRepository;
import com.pharmacy.repository.patient.impl.PatientRepositoryImpl;
import com.pharmacy.services.patient.PatientService;
/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */

public class PatientServiceImpl extends IntentService implements PatientService
{
    private final PatientRepository repo;

    private static final String ACTION_ADD = "com.pharmacyandroid.services.patient.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.pharmacyandroid.services.patient.impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.pharmacyandroid.services.patient.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.pharmacyandroid.services.patient.impl.extra.UPDATE";

    private static PatientServiceImpl service = null;
    public static PatientServiceImpl getInstance() {
        if (service == null)
            service = new PatientServiceImpl();
        return service;
    }
    public PatientServiceImpl()
    {
        super("PatientServiceImpl");
        repo = new PatientRepositoryImpl(App.getAppContext());
    }

    @Override
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PatientImpl patient = (PatientImpl) intent.getSerializableExtra(EXTRA_ADD);
                postContact(patient);
            } else if (ACTION_UPDATE.equals(action)) {
                final PatientImpl patient = (PatientImpl) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(patient);
            }
        }
    }

    private void updateContact(PatientImpl patient)
    {
            PatientImpl updatedContact = repo.update(patient);
            repo.save(updatedContact);
    }

    private void postContact(PatientImpl patient)
    {
            PatientImpl createdContact = repo.update(patient);
            repo.save(createdContact);
    }

    @Override
    public void addPatientImpl(Context context, PatientImpl patient)
    {
        Intent intent = new Intent(context, PatientServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, patient);
    }

    @Override
    public void updatePatientImpl(Context context, PatientImpl patient)
    {
        Intent intent = new Intent(context, PatientServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, patient);
        context.startService(intent);
    }
}
