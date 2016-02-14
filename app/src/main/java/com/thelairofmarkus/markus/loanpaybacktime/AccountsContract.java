package com.thelairofmarkus.markus.loanpaybacktime;

import android.provider.BaseColumns;

/**
 * Created by markus on 14.2.2016.
 */
public final class AccountsContract {

    public static abstract class AccountTable {
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_PERSON_NAME = "personName";
        public static final String COLUMN_NAME_ACCOUNT_NUMBER = "accountNumber";
        private static final String TEXT_TYPE = " TEXT";
        private static final String VARCHAR = " VARCHAR";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ACCOUNTS =
                "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_NAME_PERSON_NAME + TEXT_TYPE + COMMA_SEP
                + COLUMN_NAME_ACCOUNT_NUMBER + VARCHAR + COMMA_SEP
                + "PRIMARY KEY (" + COLUMN_NAME_PERSON_NAME + COMMA_SEP + COLUMN_NAME_ACCOUNT_NUMBER + "))";

        public static final String SQL_DELETE_ACCOUNTS =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
