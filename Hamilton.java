import java.io.*;
import java.util.*;

class Account {
    String id;
    double balance;
    String type;

    Account(String id, int amount, String type) {
        this.id = id;
        this.balance = amount;
        this.type = type;
    }

    void deposit(int amount) {
        balance += amount;
    }

    void withdraw(int amount) {
        balance -= amount;
    }
}

class Transaction {
    String type;
    String accountNumber;
    int amount;
    String accountType;

    Transaction(String type, String accountNumber, int amount) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    Transaction(String type, String accountNumber, int amount, String accountType) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.accountType = accountType;
    }
}

class Hamilton {

    public static void main(String[] args) throws  Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        // Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("hamilton.dat"));

        int cases = Integer.parseInt(scanner.nextLine());
        Map<String, Account> accountBook = new HashMap<>();

        while (cases-- > 0) {
            Transaction transaction;
            String accountType = null;

            String transactionType = scanner.next();
            String id = scanner.next();
            int amount = scanner.nextInt();

            if (transactionType.equals("OPEN")) {
                accountType = scanner.next();
                transaction = new Transaction(transactionType, id, amount, accountType);
            } else {
                transaction = new Transaction(transactionType, id, amount);
            }

            if (!isValidTransaction(transaction, accountBook)) continue;
            if (!canOpenAccount(transaction)) continue;
            if (!hasFunds(transaction, accountBook)) continue;

            processTransaction(transaction, accountBook);

        }
        scanner.close();
    }

    static boolean canOpenAccount(Transaction transaction) {
        if (!transaction.type.equals("OPEN")) return true;
        if ((transaction.accountType.equals("CHECKING") && transaction.amount < 100) || (transaction.accountType.equals("SAVING") && transaction.amount < 1500)) {
            System.out.println("INSUFFICIENT STARTING FUNDS FOR ACCOUNT CREATION");
            return false;
        }
        return true;
    }

    static boolean hasFunds(Transaction transaction, Map<String, Account> accountBook) {
        if (!transaction.type.equals("WITHDRAW")) return true;

        Account account = accountBook.get(transaction.accountNumber);
        if (account != null && (account.balance < transaction.amount)) {
            System.out.println("INSUFFICIENT FUNDS FOR REQUESTED WITHDRAWAL");
            return false;
        }
        return true;
    }

    static boolean isValidTransaction(Transaction transaction, Map<String, Account> accountBook) {
        /*
        If a request qualifies for more than one error message, prioritize the error message for the incorrect
        format account number, then that for a value less than 0, then any others.
        */

        if ((transaction.accountNumber.length() != 6) || (Integer.parseInt(transaction.accountNumber) <= 0)) {
           System.out.println("HISTORY HAS ITS EYES ON YOU");
           return false;
        }

        if (transaction.type.equals("DEPOSIT") || transaction.type.equals("WITHDRAW")) {
            if (!accountBook.containsKey(transaction.accountNumber)) {
                System.out.println("HISTORY HAS ITS EYES ON YOU");
                return false;
            }
        }

        if (transaction.amount < 0) {
            System.out.println("I AM NOT THROWING AWAY MY SHOT");
            return false;
        }
        return true;
    }

    static void processTransaction(Transaction transaction, Map<String, Account> accountBook) {

        if (transaction.type.equals("OPEN")) {
            String newId = transaction.accountNumber;
            if (accountBook.containsKey(transaction.accountNumber)) {
                newId = bitFlipper(transaction.accountNumber, accountBook);
            };

            accountBook.put(newId, new Account(newId, transaction.amount, transaction.accountType));
            System.out.println(newId);
            return;
        }

        Account account = accountBook.get(transaction.accountNumber);
        switch (transaction.type) {
            case "WITHDRAW":
                account.withdraw(transaction.amount);
                break;
            case "DEPOSIT":
                account.deposit(transaction.amount);
                break;
            default:
        }

        System.out.printf("%.2f\n", account.balance);
    }

    static String bitFlipper(String id, Map<String, Account> accountBook) {
        int currentBitIndex = 0;
        int idInt = Integer.parseInt(id);
        int currentId = idInt;

        while (accountBook.containsKey(String.format("%06d", currentId))) {
            currentId = idInt ^ (1 << currentBitIndex);
            currentBitIndex++;
        }
        return String.format("%06d", currentId);
    }
}


