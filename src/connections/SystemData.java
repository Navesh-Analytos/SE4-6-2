package connections;

import entities.LoginCredentials;

public class SystemData {  
        
    private LoginCredentials currentUser;

    public LoginCredentials getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(LoginCredentials currentUser) {
        this.currentUser = currentUser;
    }      
}
