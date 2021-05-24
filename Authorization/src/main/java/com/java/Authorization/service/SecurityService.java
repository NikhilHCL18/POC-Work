package com.java.Authorization.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
