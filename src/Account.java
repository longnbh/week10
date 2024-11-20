import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Account {
    public static final String CHECKING = "CHECKING";
    public static final String SAVINGS = "SAVINGS";
    protected long accountNumber;
    protected double balance;
    protected List<Transaction> transactionList = new ArrayList<>();

    public Account() {}

    /**Consturo.
     *
     * @param accountNumber long
     * @param balance double
     */
    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionList = new ArrayList<>();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    /**With draw and handle exeception.
     *
     * @param amount double
     * @throws InvalidFundingAmountException exc
     * @throws InsufficientFundsException exc
     */
    public void doWithdrawing(double amount) throws BankException {
        if (amount < 0) {
            throw new InvalidFundingAmountException(amount);
        } else if (amount > balance) {
            throw new InsufficientFundsException(amount);
        } else {
            balance -= amount;
        }
    }

    /**Deposit and handle exception.
     *
     * @param amount money
     * @throws InvalidFundingAmountException excp
     */
    public void doDepositing(double amount) throws InvalidFundingAmountException {
        if (amount < 0) {
            throw new InvalidFundingAmountException(amount);
        } else {
            balance += amount;
        }
    }

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

    /**History.
     *
     * @return string
     */
    public String getTransactionHistory() {
        StringBuilder info = new StringBuilder("Lịch sử giao dịch của tài khoản "
                + accountNumber + ":\n");
        for (Transaction trans : transactionList) {
            info.append(trans.getTransactionSummary()).append("\n");
        }
        return info.toString();
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account account) || (o == null)) {
            return false;
        }
        return accountNumber == account.accountNumber;
    }
}