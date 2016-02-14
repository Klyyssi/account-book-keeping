package com.thelairofmarkus.markus.loanpaybacktime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by markus on 13.2.2016.
 */
public class AccountRepository implements IAccountRepository {

    private AccountsDbHelper loansDb;

    public AccountRepository(Context context) {
        loansDb = new AccountsDbHelper(context);
    }

    @Override
    public void put(AccountLine accountLine) {
        SQLiteDatabase db = loansDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AccountsContract.AccountTable.COLUMN_NAME_PERSON_NAME, accountLine.person.name);
        values.put(AccountsContract.AccountTable.COLUMN_NAME_ACCOUNT_NUMBER, accountLine.account.accountNumber);
        db.insert(
                AccountsContract.AccountTable.TABLE_NAME,
                AccountsContract.AccountTable.COLUMN_NAME_PERSON_NAME,
                values);

        db.close();
    }

    @Override
    public List<AccountLine> getAccounts() {
        SQLiteDatabase db = loansDb.getReadableDatabase();
        List<AccountLine> accounts = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select * from " + AccountsContract.AccountTable.TABLE_NAME, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Person person = Person.fromString(cursor.getString(cursor.getColumnIndex(AccountsContract.AccountTable.COLUMN_NAME_PERSON_NAME)));
            Account account = Account.fromString(cursor.getString(cursor.getColumnIndex(AccountsContract.AccountTable.COLUMN_NAME_ACCOUNT_NUMBER)));
            accounts.add(new AccountLine(person, account));
            cursor.moveToNext();
        }

        db.close();

        return accounts;
    }

    @Override
    public void remove(AccountLine accountLine) {
        SQLiteDatabase db = loansDb.getWritableDatabase();
        String[] selectionArgs = { accountLine.person.name, accountLine.account.accountNumber };
        db.delete(
                AccountsContract.AccountTable.TABLE_NAME,
                AccountsContract.AccountTable.COLUMN_NAME_PERSON_NAME + " LIKE ? AND "
                + AccountsContract.AccountTable.COLUMN_NAME_ACCOUNT_NUMBER + "=?"
                , selectionArgs);
        db.close();
    }
}
