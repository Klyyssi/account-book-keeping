package com.thelairofmarkus.markus.loanpaybacktime.repository;

import com.thelairofmarkus.markus.loanpaybacktime.domain.AccountLine;

import java.util.List;

/**
 * Created by Markus Mulkahainen on 12.2.2016.
 */
public interface IAccountRepository {

    /**
     * Adds new AccountLine to the repository
     * @param accountLine account to add
     */
    void put(AccountLine accountLine);

    /**
     * Returns all AccountLines that this repository contains
     * @return list of AccountLines
     */
    List<AccountLine> getAccounts();

    /**
     * Removes an AccountLine from the repository
     * @param accountLine AccountLine to remove
     */
    void remove(AccountLine accountLine);
}
