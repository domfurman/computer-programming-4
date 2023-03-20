package pl.dfurman.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal balance;
    private BigDecimal credit;
    private BigDecimal limit;

    public CreditCard(String cardNumber) {

    }

    public void assignCredit(BigDecimal creditAmount) {
        if (isBelowThreshold(creditAmount)) {
            throw new CreditLimitBelowThresholdException();
        }

        if (isAlreadyAssigned()) {
            throw new CantAssignCreditTwiceException();
        }
        this.balance = creditAmount;
        this.credit = creditAmount;
    }

    private boolean isAlreadyAssigned() {
        return credit != null;
    }

    private boolean isBelowThreshold(BigDecimal creditAmount) {
        return creditAmount.compareTo(BigDecimal.valueOf(100)) < 0;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void assignLimit(BigDecimal amount) {
        this.limit = amount;
    }

    public BigDecimal wihtdraw(BigDecimal amount) {
        if (amount.compareTo(balance) < 0) {
            this.balance = this.balance.subtract(amount);
        }

       if (amount.compareTo(limit) < 0) {
           this.balance = this.balance.subtract(amount);
       }
        return this.balance;
    }

}
