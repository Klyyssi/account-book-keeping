package com.thelairofmarkus.markus.loanpaybacktime.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thelairofmarkus.markus.loanpaybacktime.repository.AccountsContract;

/**
 * Created by markus on 14.2.2016.
 */
public class AccountsDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Accounts.db";

    public AccountsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AccountsContract.AccountTable.SQL_CREATE_ACCOUNTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Implement when DATABASE_VERSION is incremented from 1
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AccountsContract.AccountTable.SQL_DELETE_ACCOUNTS);
        onCreate(db);
    }
}
