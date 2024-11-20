import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    public static final String CHECKING = "CHECKING";
    public static final String SAVINGS = "SAVINGS";
    protected long accountNumber;
    protected double balance;
    protected List<Transaction> transactionList;

    public Account() {
        transactionList = new ArrayList<>();
    }

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
    public void doWithdrawing(double amount) throws InvalidFundingAmountException,
            InsufficientFundsException {
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
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account account = (Account) obj;
            return accountNumber == account.accountNumber;
        }
        return false;
    }
}