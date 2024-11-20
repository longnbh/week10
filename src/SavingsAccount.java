public class SavingsAccount extends Account {
    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**With draw money.
     *
     * @param amount money used
     */
    public void withdraw(double amount) {
        try {
            if (amount > 1000) {
                throw new InvalidFundingAmountException(amount);
            }

            if (getBalance() - amount < 5000) {
                throw new InsufficientFundsException(amount);
            }

            doWithdrawing(amount);
            Transaction transaction = new Transaction(Transaction.TYPE_WITHDRAW_SAVINGS,
                    amount, balance + amount,
                    balance);
            super.addTransaction(transaction);
        } catch (InvalidFundingAmountException e) {
            System.out.println("Da vuot qua so tien cho phep rut - " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Khong du so du toi thieu - " + e.getMessage());
        }
    }

    /**Deposit money.
     *
     * @param amount money used
     */
    public void deposit(double amount) {
        try {
            doDepositing(amount);
            Transaction transaction = new Transaction(
                    Transaction.TYPE_DEPOSIT_SAVINGS,
                    amount,
                    balance - amount,
                    balance
            );
            super.addTransaction(transaction);
        } catch (InvalidFundingAmountException e) {
            System.out.println("Deposit failed: Invalid amount - " + e.getMessage());
        }
    }
}
