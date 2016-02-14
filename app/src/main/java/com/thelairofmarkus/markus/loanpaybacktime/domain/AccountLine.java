package com.thelairofmarkus.markus.loanpaybacktime.domain;

import com.thelairofmarkus.markus.loanpaybacktime.domain.Account;
import com.thelairofmarkus.markus.loanpaybacktime.domain.Person;

/**
 * Created by markus on 12.2.2016.
 */
public class AccountLine {

    public final Person person;
    public final Account account;

    public AccountLine(Person person, Account account) {
        this.person = person;
        this.account = account;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", person, account);
    }
}
