package com.adwidian.ramaniapi.projectkp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2018/02/20.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<ModelFungsi> getQuotes() {
        List<ModelFungsi> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM puskesmas", null);
        cursor.moveToFirst();
        ModelFungsi model;
        while (!cursor.isAfterLast()) {
            Log.d("data", cursor.getString(1) + " "+ cursor.getString(2) + " " + cursor.getDouble(3) + " "+ cursor.getDouble(4));
            model = new ModelFungsi(cursor.getString(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4));
            list.add(model);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
