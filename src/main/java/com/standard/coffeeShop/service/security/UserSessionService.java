package com.standard.coffeeShop.service.security;

public interface UserSessionService {

    void unregisterUserSession(String uid, String idSessionHashed);

}
