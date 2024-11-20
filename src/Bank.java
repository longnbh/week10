import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;

public class Bank {
    private List<Customer> customerList = new ArrayList<>();

    /**Return list.
     *
     * @param inputStream ipstr
     */
    public void readCustomerList(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            String nameAndId = scanner.nextLine().trim();

            // Kiểm tra dòng không hợp lệ
            if (nameAndId.isEmpty() || !nameAndId.contains(" ")) {
                throw new IllegalArgumentException("Invalid customer information "
                        + "format: " + nameAndId);
            }

            // Tách chuỗi dựa trên khoảng trắng
            String[] parts = nameAndId.split(" ");

            // Ghép phần đầu thành họ tên, phần cuối là số CMND
            String fullName = String.join(" ", Arrays.copyOfRange(parts,
                    0, parts.length - 1)); // Ghép từ phần đầu đến phần áp chót
            long idNumber;
            try {
                idNumber = Long.parseLong(parts[parts.length - 1]); // Phần cuối cùng là CMND
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid ID number "
                        + "format: " + parts[parts.length - 1], e);
            }

            // Tạo khách hàng
            Customer customer = new Customer(idNumber, fullName);

            // Đọc thông tin tài khoản
            while (scanner.hasNextLine()) {
                String accountLine = scanner.nextLine().trim();

                // Nếu gặp dòng trống, chuyển sang khách hàng tiếp theo
                if (accountLine.isEmpty()) {
                    break;
                }

                // Kiểm tra định dạng dòng tài khoản
                String[] accountData = accountLine.split(" ");
                if (accountData.length != 3) {
                    throw new IllegalArgumentException("Invalid account format: " + accountLine);
                }

                long accountNumber = Long.parseLong(accountData[0].trim());
                String accountType = accountData[1].trim();
                double balance = Double.parseDouble(accountData[2].trim());

                // Xử lý loại tài khoản
                Account account;
                if (accountType.equals("CHECKING")) {
                    account = new CheckingAccount(accountNumber, balance);
                } else if (accountType.equals("SAVINGS")) {
                    account = new SavingsAccount(accountNumber, balance);
                } else {
                    throw new IllegalArgumentException("Unknown account type: " + accountType);
                }

                customer.addAccount(account);
            }

            // Thêm khách hàng vào danh sách
            customerList.add(customer);
        }

        scanner.close();
    }

    /**Return in name order.
     *
     * @return string
     */
    public String getCustomersInfoByNameOrder() {
        List<Customer> nameOrderedCustomers = new ArrayList<>(customerList);
        nameOrderedCustomers.sort(Comparator.comparing(Customer::getFullName));
        StringBuilder result = new StringBuilder();
        for (Customer customer : nameOrderedCustomers) {
            result.append(customer.getCustomerInfo());
        }
        return result.toString();
    }

    /**Return in id order.
     *
     * @return string
     */
    public String getCustomersInfoByIdOrder() {
        List<Customer> idOrderedCustomers = new ArrayList<>(customerList);
        idOrderedCustomers.sort(Comparator.comparing(Customer::getIdNumber));
        StringBuilder result = new StringBuilder();
        for (Customer customer : idOrderedCustomers) {
            result.append(customer.getCustomerInfo());
        }
        return result.toString();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
