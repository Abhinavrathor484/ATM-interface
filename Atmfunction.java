package AtmInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(Account destinationAccount, double amount) {
        if (amount <= balance) {
            balance -= amount;
            destinationAccount.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.getType() + ": " + transaction.getAmount());
        }
    }
}



public class Atmfunction {
	
	 private static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        Account account = new Account("12345");

	        while (true) {
	            printMenu();
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter amount to deposit: ");
	                    double depositAmount = scanner.nextDouble();
	                    account.deposit(depositAmount);
	                    break;
	                case 2:
	                    System.out.print("Enter amount to withdraw: ");
	                    double withdrawAmount = scanner.nextDouble();
	                    account.withdraw(withdrawAmount);
	                    break;
	                case 3:
	                    System.out.print("Enter destination account number: ");
	                    String destinationAccountNumber = scanner.next();
	                    System.out.print("Enter amount to transfer: ");
	                    double transferAmount = scanner.nextDouble();
	                    Account destinationAccount = new Account(destinationAccountNumber);
	                    account.transfer(destinationAccount, transferAmount);
	                    break;
	                case 4:
	                    account.printTransactionHistory();
	                    break;
	                case 5:
	                    System.out.println("Thank you for using the ATM. Goodbye!");
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }

	    public static void printMenu() {
	        System.out.println("ATM Menu");
	        System.out.println("1. Deposit");
	        System.out.println("2. Withdraw");
	        System.out.println("3. Transfer");
	        System.out.println("4. View Transaction History");
	        System.out.println("5. Quit");
	        System.out.print("Enter your choice: ");
	    }
	

}
