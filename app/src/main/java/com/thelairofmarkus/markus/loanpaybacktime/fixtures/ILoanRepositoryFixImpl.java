package com.thelairofmarkus.markus.loanpaybacktime.fixtures;

import com.thelairofmarkus.markus.loanpaybacktime.Account;
import com.thelairofmarkus.markus.loanpaybacktime.ILoanRepository;
import com.thelairofmarkus.markus.loanpaybacktime.LoanLine;
import com.thelairofmarkus.markus.loanpaybacktime.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by markus on 12.2.2016.
 */
public class ILoanRepositoryFixImpl implements ILoanRepository {

    List<LoanLine> loans = new ArrayList<LoanLine>(
            Arrays.asList(
                    new LoanLine(Person.fromString("Jaska Jokunen"), Account.fromString("FI58 1234 2345 1234 58")),
                    new LoanLine(Person.fromString("Arnold"), Account.fromString("FI12 1234 1234 1234 12"))
            )
    );

    @Override
    public void put(LoanLine loan) {
        loans.add(loan);
    }

    @Override
    public List<LoanLine> getLoans() {
        return loans;
    }

    @Override
    public List<LoanLine> removeLoan(LoanLine loan) {
        loans.remove(loan);
        return loans;
    }
}
