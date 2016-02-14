package com.thelairofmarkus.markus.loanpaybacktime;

import java.util.List;

/**
 * Created by Markus Mulkahainen on 12.2.2016.
 */
public interface ILoanRepository {

    /**
     * Adds new AccountLine to the repository
     * @param loan account to add
     */
    void put(LoanLine loan);

    /**
     * Returns all AccountLines that this repository contains
     * @return list of AccountLines
     */
    List<LoanLine> getLoans();

    /**
     * Removes an AccountLine from the repository
     * @param loan AccountLine to remove
     */
    void removeLoan(LoanLine loan);
}
