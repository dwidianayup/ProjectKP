package com.adwidian.ramaniapi.projectkp;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by apple on 2018/02/20.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper{
    private static final String DATABASE_NAME = "projectkp.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
