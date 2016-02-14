package com.thelairofmarkus.markus.loanpaybacktime.domain;

/**
 * Created by markus on 12.2.2016.
 */
public class Account {

    public final String accountNumber;
    private static final String allowedCharSeq = "[a-zA-Z]{2}[0-9]{2} ?[a-zA-Z0-9]{4} ?[0-9]{4} ?[0-9]{4} ?[0-9]{2}";

    private Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() { return accountNumber; }

    public static Account fromString(String accountNumber) {
        return accountNumber.matches(allowedCharSeq) ?
                new Account(accountNumber) :
                getDefault();
    }

    public static Account getDefault() {
        return SingletonHolder.DEFAULT;
    }

    private static class SingletonHolder {
        private static final Account DEFAULT = new Account("N/A");
    }
}
