package com.standard.coffeeshop.service.security;

public interface UserSessionService {

    void unregisterUserSession(String uid, String idSessionHashed);

}
