package com.thelairofmarkus.markus.loanpaybacktime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.thelairofmarkus.markus.loanpaybacktime.fixtures.ILoanRepositoryFixImpl;

public class MainViewActivity extends AppCompatActivity {

    private ILoanRepository loanRepository = new ILoanRepositoryFixImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        updateLoans();
    }

    public void newAccount(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("New loan");
        final EditText txtName = new EditText(this);
        final EditText txtAccNumber = new EditText(this);

        builder.setView(txtName);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println(txtName.getText());
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
