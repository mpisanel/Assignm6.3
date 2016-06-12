package com.pharmacy.services.doctor.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pharmacy.conf.util.App;
import com.pharmacy.domain.doctor.impl.DoctorImp;
import com.pharmacy.repository.doctor.impl.DoctorRepositoryImpl;
import com.pharmacy.services.doctor.DoctorService;
/**
 * I used intentService, because it takes care of the life cycle of the thread, and uses a looper, which helps the scheduler.
 * It also makes sure only one instance is running, and queues other calls
 */
public class DoctorServiceImpl extends IntentService implements DoctorService
{
    private final DoctorRepositoryImpl repo;
    private static final String ACTION_ADD = "com.pharmacyandroid.services.doctor.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.pharmacyandroid.services.doctor.impl.action.BAZ";

    private static final String EXTRA_ADD = "com.pharmacyandroid.services.doctor.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.pharmacyandroid.services.doctor.impl.extra.UPDATE";

    private static DoctorServiceImpl service = null;
    public static DoctorServiceImpl getInstance() {
        if (service == null)
            service = new DoctorServiceImpl();
        return service;
    }
    public DoctorServiceImpl()
    {
        super("DoctorServiceImpl");
        repo = new DoctorRepositoryImpl(App.getAppContext());
    }

    @Override
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final DoctorImp doctorImp = (DoctorImp) intent.getSerializableExtra(EXTRA_ADD);
                postContact(doctorImp);
            } else if (ACTION_UPDATE.equals(action)) {
                final DoctorImp doctorImp = (DoctorImp) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(doctorImp);
            }
        }
    }

    private void updateContact(DoctorImp doctorImp)
    {
        DoctorImp updatedContact = repo.update(doctorImp);
        repo.save(updatedContact);
    }

    private void postContact(DoctorImp patient)
    {
        DoctorImp createdContact = repo.update(patient);
        repo.save(createdContact);
    }

    @Override
    public void addDoctorImpl(Context context, DoctorImp doctorImp)
    {
        Intent intent = new Intent(context, DoctorImp.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, doctorImp);
    }

    @Override
    public void updateDoctorImpl(Context context, DoctorImp doctorImp)
    {
        Intent intent = new Intent(context, DoctorImp.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, doctorImp);
        context.startService(intent);
    }
}
