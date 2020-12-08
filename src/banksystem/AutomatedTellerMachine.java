/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banksystem;
import java.util.Scanner;
/**
 *
 * @author Krikor Astour
 */
public class AutomatedTellerMachine {
    private String atmId;
    private User user;
    private Account account;
    private static int nextId = 1;

    public AutomatedTellerMachine() {
        this.atmId = String.format("%06d", nextId++);
        this.user = null;
        this.account = null;
    }
    
    public AutomatedTellerMachine(String atmId) {
        this.atmId = atmId;
        this.user = null;
        this.account = null;
    }
    
    public AutomatedTellerMachine(AutomatedTellerMachine automatedTellerMachine) {
        this.atmId = automatedTellerMachine.atmId;
        this.user = new User(automatedTellerMachine.user);
        this.account = new Account(automatedTellerMachine.account);
    }
    
    public boolean equals(AutomatedTellerMachine automatedTellerMachine) {
        return this.atmId.equals(automatedTellerMachine.atmId);
    }
    
    public String toString() {
        return String.format("%-10s: %s", "ATM ID", atmId);
    }

    public String getAtmId() {
        return atmId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static void setNextId(int nextId) {
        AutomatedTellerMachine.nextId = nextId;
    }

    public User getUser() {
        return user;
    }

    public Account getAccount() {
        return account;
    }

    public static int getNextId() {
        return nextId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }
    
    /**
     * to print a welcome message
     */
    public void printWelcome() {
        System.out.println("Welcome to our ATM");
    }
    
    public void readUserId() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please enter your ID");
        String inputId = console.next();
        
        for(int i = 0; i < Bank.getUsers().size(); i++)
            if(inputId.equals(Bank.getUsers().get(i).getUserId())){
                user = Bank.getUsers().get(i);
                return;
            }
        System.out.println("your ID is invalid");
        user = null;
    }
    
    /**
     * to make the user input the password the user has 3 tries before the account blocks
     * @param user the user object of the user
     * @return if the password is correct
     */
    public boolean inputPin() {
        Scanner console = new Scanner(System.in);
        int maxTry = 3;
        
        for(int i = 0; i < maxTry; i++) {
            System.out.println("Please Enter your pin");
            String password = console.next();
            if(user.getPassword().equals(password))
                return true;
            System.out.println("Wrong!");
        }
        System.out.println("you have entered Wrong pin 3 times");
        return false;
    }
    
    /**
     * to ask the user from which account he want to operate
     */
    public void chooseAccount() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please choose the account you want: " + 
                "\n\t1. Checking account" + "\n\t2. Saving account");
        int accountChoice = console.nextInt();
        account = accountChoice == 1 ? user.getCheckingAccount() : user.getSavingAccount();
    }
    
    /**
     * to ask the user what does he want to do 
     * @return the operation
     */
    public int chooseOperation() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("please chos the operation: " + "\n\t1. Withdraw" +
                "\n\t2. Deposit" + "\n\t3. Display Balance");
        int operation = console.nextInt();
        return operation;
    }
    
    /**
     * to withdraw from the ATM
     * @param account the account the user wants to withdraw from
     * @return true if there is enough money
     */
    public boolean withdraw() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("enter the withdaw amount");
        double amount = console.nextDouble();
        if (account.getBalance() < amount) {
            System.out.println("unavailable");
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdraw Sucessed");
        user.getHistory().add(new Record("Withdraw", amount, atmId));
        return true;
    }
    
    /**
     * to deposit money into the account
     * @param account the account of the user
     * @return ture
     */
    public boolean deposit() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Enter the amount you want to deposit");
        double amount = console.nextDouble();
        
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit completed");
        user.getHistory().add(new Record("Deposit", amount, atmId));
        return true;
    }
    
    /**
     * to display the current balance
     * @param account the account of the user
     */
    public void displayBalance() {
        System.out.printf("%-10s: %.2f\n", "Amount Available", account.getBalance());
    }
    
    /**
     * to ask the user if he wants to continue or no
     * @return true if the user wants to continue
     */
    public boolean doesContinue() {
        Scanner console = new Scanner(System.in);
                
        System.out.println("Do you want to do another operation");
        System.out.println("0. No");
        System.out.println("1. Yes");
        int answer = console.nextInt();
        
        return answer == 1;
    }
    
    /**
     * to print thank you
     */
    public void printGoodBye() {
        System.out.println("Thank you!");
    }
    
    public void pipeline() {
        printWelcome();
        readUserId();
        if (user == null)
            System.exit(1);
        if (!inputPin())
            System.exit(2);
        
        chooseAccount();
        do {
            int oper = chooseOperation();
            switch (oper) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                default:
                    displayBalance();
            }
        } while(doesContinue());
        printGoodBye();
    }
}
