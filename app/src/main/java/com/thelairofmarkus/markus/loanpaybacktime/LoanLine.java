package com.thelairofmarkus.markus.loanpaybacktime;

/**
 * Created by markus on 12.2.2016.
 */
public class LoanLine {

    public final Person person;
    public final Account account;

    public LoanLine(Person person, Account account) {
        this.person = person;
        this.account = account;
    }
}
