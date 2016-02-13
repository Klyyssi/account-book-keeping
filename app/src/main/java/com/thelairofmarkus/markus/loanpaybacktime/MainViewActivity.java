package com.thelairofmarkus.markus.loanpaybacktime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thelairofmarkus.markus.loanpaybacktime.fixtures.ILoanRepositoryFixImpl;

public class MainViewActivity extends AppCompatActivity {

    private ILoanRepository loanRepository = new ILoanRepositoryFixImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        //context menu
        View listView = (View) findViewById(R.id.listView);
        registerForContextMenu(listView);


        updateLoans();
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
        ListView loansList = (ListView) findViewById(R.id.listView);
        LoanLine selectedLine = (LoanLine) loansList.getAdapter().getItem(info.position);
        loanRepository.removeLoan(selectedLine);
        updateLoans();
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

                LoanLine accLine = new LoanLine(person, account);
                loanRepository.put(accLine);
                updateLoans();
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

    private void updateLoans() {
        ListView loansList = (ListView) findViewById(R.id.listView);

        ArrayAdapter<LoanLine> adapter = new ArrayAdapter<LoanLine>(
                this,
                android.R.layout.simple_list_item_1,
                loanRepository.getLoans());

        loansList.setAdapter(adapter);
    }
}
