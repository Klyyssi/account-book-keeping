package com.thelairofmarkus.markus.loanpaybacktime;

import java.util.List;

/**
 * Created by Markus Mulkahainen on 12.2.2016.
 */
public interface ILoanRepository {

    void put(LoanLine loan);

    List<LoanLine> getLoans();

    List<LoanLine> removeLoan(LoanLine loan);
}
