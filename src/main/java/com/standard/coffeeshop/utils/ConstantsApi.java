package com.standard.coffeeshop.utils;

public class ConstantsApi {

    private ConstantsApi(){
    }

    public static final String BASE_URL = "/api/v1";
    public static final String MANAGE = BASE_URL + "/manage";
    public static final String PRODUCT = MANAGE + "/product";
    public static final String DISCOUNT = MANAGE + "/discount";
    public static final String ORDER = MANAGE + "/order";
    public static final String CUSTOMER = MANAGE + "/customer";

    public static final String AUTHENTICATION = BASE_URL + "/authentication";

    public static final String USER = AUTHENTICATION +"/user"; // maybe this should be authentication/user

}
