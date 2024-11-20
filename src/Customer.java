import java.util.ArrayList;
import java.util.List;

public class Customer {
    private long idNumber;
    private String fullName;
    private List<Account> accountList;

    public Customer() {
        accountList = new ArrayList<>();
    }

    /**Get info.
     *
     * @return a string
     */
    public String getCustomerInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Số CMND: ").append(idNumber).append(". Họ tên: ")
                .append(fullName).append(".\n");
//        .append(" " + idNumber)
//        for (Account acc : accountList) {
//            info.append(acc.getAccountNumber()).append(" ").
//            append(acc instanceof CheckingAccount ?
//                    "CHECKING" : "SAVINGS").append(" ").append(acc.getBalance()).append("\n");
        //      }
        return info.toString();
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public void removeAccount(Account account) {
        accountList.remove(account);
    }

    public Customer(long idNumber, String fullName) {
        this.idNumber = idNumber;
        this.fullName = fullName;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }
}
