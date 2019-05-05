package com.idiotleon.tutorialdagger2withkotlin.modules.provides.persist;

import android.util.Log;

public interface DAO {
    static DAO get() {
        return new DAOImpl();
    }

    void store(Transaction transaction, String data);
}

class DAOImpl implements DAO {
    private final String TAG = DAOImpl.class.getSimpleName();

    @Override
    public void store(Transaction transaction, String data) {
        Log.d(TAG, "@" + transaction + ", storing: " + data);
    }
}