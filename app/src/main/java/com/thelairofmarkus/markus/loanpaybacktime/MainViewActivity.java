package com.thelairofmarkus.markus.loanpaybacktime;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.thelairofmarkus.markus.loanpaybacktime.fixtures.ILoanRepositoryFixImpl;

public class MainViewActivity extends AppCompatActivity {

    private ILoanRepository loanRepository = new ILoanRepositoryFixImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
    }

    public void newLoan(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("New loan");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
