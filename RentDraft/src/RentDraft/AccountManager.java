/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RentDraft;

/**
 *
 * @author paolo
 */
public class AccountManager {
    
    private String currentUser;
    private String currentPass;
    
    private static final AccountManager instance = new AccountManager();
    
    public static AccountManager getInstance(){
        return instance;
    }
    
    public void initialize (String user, String pass){
        this.currentUser = user;
        this.currentPass = pass;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getCurrentPass() {
        return currentPass;
    }

    
    
    
}
