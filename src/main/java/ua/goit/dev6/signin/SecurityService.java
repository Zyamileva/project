package ua.goit.dev6.signin;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}