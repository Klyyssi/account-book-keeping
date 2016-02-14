package com.thelairofmarkus.markus.loanpaybacktime.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.thelairofmarkus.markus.loanpaybacktime.domain.AccountLine;
import com.thelairofmarkus.markus.loanpaybacktime.repository.AccountRepository;
import com.thelairofmarkus.markus.loanpaybacktime.repository.IAccountRepository;
import com.thelairofmarkus.markus.loanpaybacktime.R;
import com.thelairofmarkus.markus.loanpaybacktime.domain.Account;
import com.thelairofmarkus.markus.loanpaybacktime.domain.Person;

public class MainViewActivity extends AppCompatActivity {

    private IAccountRepository accountRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        //context menu
        View listView = (View) findViewById(R.id.listView);
        registerForContextMenu(listView);

        //create repository
        accountRepository = new AccountRepository(getApplicationContext());

        refreshAccounts();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listView) {
            menu.add("Delete");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ListView accountsList = (ListView) findViewById(R.id.listView);
        AccountLine selectedLine = (AccountLine) accountsList.getAdapter().getItem(info.position);
        accountRepository.remove(selectedLine);
        refreshAccounts();
        return true;
    }

    public void newAccount(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("New account");

        View newAccountView = getLayoutInflater().inflate(R.layout.new_account_view, null);
        final EditText txtName = (EditText) newAccountView.findViewById(R.id.txtName);
        final EditText txtAccNumber = (EditText) newAccountView.findViewById(R.id.txtAccNumber);

        builder.setView(newAccountView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Person person = Person.fromString(txtName.getText().toString());
                Account account = Account.fromString(txtAccNumber.getText().toString());

                if (person == Person.getDefault()) {
                    showError(Messages.ERROR_PERSON_NAME_MUST_CONTAIN_LETTERS_FROM_A_TO_Z);
                    return;
                }

                if (account == Account.getDefault()) {
                    showError(Messages.ERROR_ACCOUNT_NUMBER_MUST_BE_FINNISH_IBAN);
                    return;
                }

                AccountLine accLine = new AccountLine(person, account);
                accountRepository.put(accLine);
                refreshAccounts();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showError(String errorMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Watch out!").setMessage(errorMsg);
        builder.show();
    }

    private void refreshAccounts() {
        ListView accountsList = (ListView) findViewById(R.id.listView);

        ArrayAdapter<AccountLine> adapter = new ArrayAdapter<AccountLine>(
                this,
                android.R.layout.simple_list_item_1,
                accountRepository.getAccounts());

        accountsList.setAdapter(adapter);
    }
}
