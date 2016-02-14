package com.thelairofmarkus.markus.loanpaybacktime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by markus on 14.2.2016.
 */
public class LoansDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Accounts.db";

    public LoansDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoansContract.LoanTable.SQL_CREATE_LOANS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Implement when DATABASE_VERSION is incremented from 1
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(LoansContract.LoanTable.SQL_DELETE_LOANS);
        onCreate(db);
    }
}
