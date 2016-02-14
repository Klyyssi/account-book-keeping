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
public class LoanRepository implements ILoanRepository {

    private LoansDbHelper loansDb;

    public LoanRepository(Context context) {
        loansDb = new LoansDbHelper(context);
    }

    @Override
    public void put(LoanLine loan) {
        SQLiteDatabase db = loansDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LoansContract.LoanTable.COLUMN_NAME_PERSON_NAME, loan.person.name);
        values.put(LoansContract.LoanTable.COLUMN_NAME_ACCOUNT_NUMBER, loan.account.accountNumber);
        db.insert(
                LoansContract.LoanTable.TABLE_NAME,
                LoansContract.LoanTable.COLUMN_NAME_PERSON_NAME,
                values);

        db.close();
    }

    @Override
    public List<LoanLine> getLoans() {
        SQLiteDatabase db = loansDb.getReadableDatabase();
        List<LoanLine> loans = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select * from " + LoansContract.LoanTable.TABLE_NAME, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Person person = Person.fromString(cursor.getString(cursor.getColumnIndex(LoansContract.LoanTable.COLUMN_NAME_PERSON_NAME)));
            Account account = Account.fromString(cursor.getString(cursor.getColumnIndex(LoansContract.LoanTable.COLUMN_NAME_ACCOUNT_NUMBER)));
            loans.add(new LoanLine(person, account));
            cursor.moveToNext();
        }

        db.close();

        return loans;
    }

    @Override
    public void removeLoan(LoanLine loan) {
        SQLiteDatabase db = loansDb.getWritableDatabase();
        String[] selectionArgs = { loan.person.name, loan.account.accountNumber };
        db.delete(
                LoansContract.LoanTable.TABLE_NAME,
                LoansContract.LoanTable.COLUMN_NAME_PERSON_NAME + " LIKE ? AND "
                + LoansContract.LoanTable.COLUMN_NAME_ACCOUNT_NUMBER + "=?"
                , selectionArgs);
        db.close();
    }
}
