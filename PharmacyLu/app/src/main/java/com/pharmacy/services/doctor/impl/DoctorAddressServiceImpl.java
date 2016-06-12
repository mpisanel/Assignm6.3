package com.pharmacy.services.doctor.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.doctor.impl.DoctorAddressImpl;
import com.pharmacy.repository.doctor.impl.DoctorAddressRepositoryImpl;
import com.pharmacy.services.doctor.DoctorAddressService;

/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */
public class DoctorAddressServiceImpl extends IntentService implements DoctorAddressService
{
    private final DoctorAddressRepositoryImpl repo;
    private static final String ACTION_ADD = "com.pharmacyandroid.services.doctor.action.ADD";
    private static final String ACTION_UPDATE = "com.pharmacyandroid.services.doctor.action.UPDATE";


    private static final String EXTRA_ADD = "com.pharmacyandroid.services.doctor.extra.ADD";
    private static final String EXTRA_UPDATE = "com.pharmacyandroid.services.doctor.extra.UPDATE";

    private static DoctorAddressServiceImpl service = null;
    public static DoctorAddressServiceImpl getInstance() {
        if (service == null)
            service = new DoctorAddressServiceImpl();
        return service;
    }
    public DoctorAddressServiceImpl()
    {
        super("DoctorAddressServiceImpl");
        repo = new DoctorAddressRepositoryImpl(App.getAppContext());
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final DoctorAddressImpl doctorAddress = (DoctorAddressImpl) intent.getSerializableExtra(EXTRA_ADD);
                postContact(doctorAddress);
            } else if (ACTION_UPDATE.equals(action)) {
                final DoctorAddressImpl doctorAddress = (DoctorAddressImpl) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(doctorAddress);
            }
        }
    }

    private void updateContact(DoctorAddressImpl patientAddress)
    {
        DoctorAddressImpl updatedContact = repo.update(patientAddress);
        repo.save(updatedContact);
    }

    private void postContact(DoctorAddressImpl doctorAddress)
    {
        DoctorAddressImpl createdContact = repo.update(doctorAddress);
        repo.save(createdContact);
    }

    @Override
    public void addDoctorAddressImpl(Context context, DoctorAddressImpl doctorAddress)
    {
        Intent intent = new Intent(context, DoctorAddressImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, doctorAddress);
    }

    @Override
    public void updateDoctorImpl(Context context, DoctorAddressImpl doctorAddress)
    {
        Intent intent = new Intent(context, DoctorAddressImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, doctorAddress);
        context.startService(intent);
    }
}
