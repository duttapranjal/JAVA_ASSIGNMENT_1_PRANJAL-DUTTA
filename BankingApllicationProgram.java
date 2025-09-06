import java.util.Scanner;
public class BankingApplication {
    // Account class as static nested class
    static class Account {
        private int accountNumber;
        private String accountHolderName;
        private double balance;
        private String email;
        private String phoneNumber;

        public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.balance = balance;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit successful. New balance: " + balance);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0) {
                if (balance >= amount) {
                    balance -= amount;
                    System.out.println("Withdrawal successful. New balance: " + balance);
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Withdrawal amount must be positive.");
            }
        }

        public void displayAccountDetails() {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Account Holder: " + accountHolderName);
            System.out.println("Balance: " + balance);
            System.out.println("Email: " + email);
            System.out.println("Phone: " + phoneNumber);
        }

        public void updateContactDetails(String newEmail, String newPhoneNumber) {
            email = newEmail;
            phoneNumber = newPhoneNumber;
            System.out.println("Contact details updated.");
        }
    }

    // UserInterface class as static nested class
    static class UserInterface {
        private Account[] accounts;
        private int accountCount;
        private Scanner scanner;

        public UserInterface(int maxAccounts) {
            accounts = new Account[maxAccounts];
            accountCount = 0;
            scanner = new Scanner(System.in);
        }

        public void mainMenu() {
            while (true) {
                System.out.println("\nWelcome to the Banking Application!");
                System.out.println("1. Create a new account");
                System.out.println("2. Deposit money");
                System.out.println("3. Withdraw money");
                System.out.println("4. View account details");
                System.out.println("5. Update contact details");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = 0;
                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                    continue;
                }

                switch (choice) {
                    case 1: createAccount(); break;
                    case 2: performDeposit(); break;
                    case 3: performWithdrawal(); break;
                    case 4: showAccountDetails(); break;
                    case 5: updateContact(); break;
                    case 6: System.out.println("Exiting. Thank you!"); return;
                    default: System.out.println("Invalid choice.");
                }
            }
        }

        private void createAccount() {
            int accountNumber = 1001 + accountCount;
            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter initial deposit amount: ");
            double deposit = 0;
            try {
                deposit = Double.parseDouble(scanner.nextLine().trim());
                if (deposit < 0) {
                    System.out.println("Deposit amount must be positive.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount.");
                return;
            }
            System.out.print("Enter email address: ");
            String email = scanner.nextLine().trim();
            System.out.print("Enter phone number: ");
            String phone = scanner.nextLine().trim();

            accounts[accountCount++] = new Account(accountNumber, name, deposit, email, phone);
            System.out.println("Account created successfully with Account Number: " + accountNumber);
        }

        private Account findAccount(int accNum) {
            for (int i = 0; i < accountCount; i++) {
                if (accounts[i].getAccountNumber() == accNum) {
                    return accounts[i];
                }
            }
            return null;
        }

        private void performDeposit() {
            System.out.print("Enter account number: ");
            int accNum = 0;
            try {
                accNum = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account number.");
                return;
            }
            Account acc = findAccount(accNum);
            if (acc != null) {
                System.out.print("Enter amount to deposit: ");
                double amt = 0;
                try {
                    amt = Double.parseDouble(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount.");
                    return;
                }
                acc.deposit(amt);
            } else {
                System.out.println("Account not found.");
            }
        }

        private void performWithdrawal() {
            System.out.print("Enter account number: ");
            int accNum = 0;
            try {
                accNum = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account number.");
                return;
            }
            Account acc = findAccount(accNum);
            if (acc != null) {
                System.out.print("Enter amount to withdraw: ");
                double amt = 0;
                try {
                    amt = Double.parseDouble(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount.");
                    return;
                }
                acc.withdraw(amt);
            } else {
                System.out.println("Account not found.");
            }
        }

        private void showAccountDetails() {
            System.out.print("Enter account number: ");
            int accNum = 0;
            try {
                accNum = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account number.");
                return;
            }
            Account acc = findAccount(accNum);
            if (acc != null) {
                acc.displayAccountDetails();
            } else {
                System.out.println("Account not found.");
            }
        }

        private void updateContact() {
            System.out.print("Enter account number: ");
            int accNum = 0;
            try {
                accNum = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account number.");
                return;
            }
            Account acc = findAccount(accNum);
            if (acc != null) {
                System.out.print("Enter new email: ");
                String email = scanner.nextLine().trim();
                System.out.print("Enter new phone number: ");
                String phone = scanner.nextLine().trim();
                acc.updateContactDetails(email, phone);
            } else {
                System.out.println("Account not found.");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(100); // Maximum allowed accounts
        ui.mainMenu();
    
    }
}
