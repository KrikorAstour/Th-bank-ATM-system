/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banksystem;
import java.util.ArrayList;

/**
 *
 * @author Krikor Astour
 */
public class Bank {
    private static ArrayList<AutomatedTellerMachine> atms = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    
    /**
     * to add a new user to the user list
     * @param userName the name of the user
     * @param pin the pin of the user
     */
    public static void addUser(String userName, String password) {
        users.add(new User(userName, password));
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
    
    
}
