package com.thelairofmarkus.markus.loanpaybacktime.fixtures;

import com.thelairofmarkus.markus.loanpaybacktime.Account;
import com.thelairofmarkus.markus.loanpaybacktime.ILoanRepository;
import com.thelairofmarkus.markus.loanpaybacktime.LoanLine;
import com.thelairofmarkus.markus.loanpaybacktime.Person;

import java.util.Arrays;
import java.util.List;

/**
 * Created by markus on 12.2.2016.
 */
public class ILoanRepositoryFixImpl implements ILoanRepository {

    List<LoanLine> loans = Arrays.asList(new LoanLine(new Person("Jaska Jokunen"), new Account("12345-6345")));

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
