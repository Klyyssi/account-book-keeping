package com.thelairofmarkus.markus.loanpaybacktime.fixtures;

import com.thelairofmarkus.markus.loanpaybacktime.Account;
import com.thelairofmarkus.markus.loanpaybacktime.IAccountRepository;
import com.thelairofmarkus.markus.loanpaybacktime.AccountLine;
import com.thelairofmarkus.markus.loanpaybacktime.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by markus on 12.2.2016.
 */
public class AccountRepositoryMockImpl implements IAccountRepository {

    List<AccountLine> loans = new ArrayList<AccountLine>(
            Arrays.asList(
                    new AccountLine(Person.fromString("Jaska Jokunen"), Account.fromString("FI58 1234 2345 1234 58")),
                    new AccountLine(Person.fromString("Arnold"), Account.fromString("FI12 1234 1234 1234 12"))
            )
    );

    @Override
    public void put(AccountLine accountLine) {
        loans.add(accountLine);
    }

    @Override
    public List<AccountLine> getAccounts() {
        return loans;
    }

    @Override
    public void remove(AccountLine accountLine) {
        loans.remove(accountLine);
    }
}
