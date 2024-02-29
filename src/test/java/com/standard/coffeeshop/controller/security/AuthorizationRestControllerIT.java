package com.standard.coffeeshop.controller.security;


import com.standard.coffeeshop.controller.customer.CustomerController;
import com.standard.coffeeshop.controller.orders.OrdersController;
import com.standard.coffeeshop.controller.products.ProductsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
 class AuthorizationRestControllerIT extends BaseIT {

    @DisplayName("Authorization Test For Order Controller")
    @Nested
    class AuthorizationTestForOrderController {

        @DisplayName("Admin should have access")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamAdmin")
         void testGetAllOrdersAccessSuccessForAdmin(String user, String pwd) throws Exception {
            mockMvc.perform(get(OrdersController.ORDER_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }

        @DisplayName("Customer should have access (Forbidden)")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamCustomer")
         void testGetAllOrdersAccessForbiddenForCustomer(String user, String pwd) throws Exception {
            mockMvc.perform(get(OrdersController.ORDER_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }

        @DisplayName("User should have access (Forbidden)")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamUser")
         void testGetAllOrdersAccessForbiddenForUser(String user, String pwd) throws Exception {
            mockMvc.perform(get(OrdersController.ORDER_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }

        @DisplayName("All Users should have access (Unauthorized)")
        @Test
         void testGetAllOrdersHttpBasicNotAuth() throws Exception {
            mockMvc.perform(get(OrdersController.ORDER_CONTROLLER_BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized());
        }
    }

    @DisplayName("Authorization Test For Customer Controller")
    @Nested
    class AuthorizationTestForCustomerController {

        @DisplayName("Admin should have access")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamAdmin")
         void testGetAllCustomersAccessSuccessForAdmin(String user, String pwd) throws Exception {
            mockMvc.perform(get(CustomerController.CUSTOMER_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }

        @DisplayName("Customer should have access")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamCustomer")
         void testGetAllCustomersAccessSuccessForCustomer(String user, String pwd) throws Exception {
            mockMvc.perform(get(CustomerController.CUSTOMER_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }

        @DisplayName("User should have access (Forbidden)")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamUser")
         void testGetAllCustomersForbiddenForForUser(String user, String pwd) throws Exception {
            mockMvc.perform(get(CustomerController.CUSTOMER_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }

        @DisplayName("All Users should have access (Unauthorized)")
        @Test
         void testGetAllCustomersHttpBasicNotAuth() throws Exception {
            mockMvc.perform(get(CustomerController.CUSTOMER_CONTROLLER_BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized());
        }
    }

    @DisplayName("Authorization Test For Product Controller")
    @Nested
    class AuthorizationTestForProductController {

        @DisplayName("Admin should have access")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamAdmin")
         void testGetAllCustomersAccessSuccessForAdmin(String user, String pwd) throws Exception {
            mockMvc.perform(get(ProductsController.PRODUCT_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }

        @DisplayName("Customer should have access (Forbidden)")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamCustomer")
         void testGetAllCustomersAccessSuccessForCustomer(String user, String pwd) throws Exception {
            mockMvc.perform(get(ProductsController.PRODUCT_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }

        @DisplayName("User should have access (Forbidden)")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamUser")
         void testGetAllCustomersForbiddenForForUser(String user, String pwd) throws Exception {
            mockMvc.perform(get(ProductsController.PRODUCT_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }

        @DisplayName("All Users should have access (Unauthorized)")
        @Test
         void testGetAllCustomersHttpBasicNotAuth() throws Exception {
            mockMvc.perform(get(ProductsController.PRODUCT_CONTROLLER_BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized());
        }
    }


}
