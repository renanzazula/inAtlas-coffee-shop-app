package com.standard.coffeeShop.controller.security;


import com.standard.coffeeShop.controller.customer.CustomerController;
import com.standard.coffeeShop.controller.orders.OrdersController;
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
public class AuthorizationRestControllerIT extends BaseIT {

    @DisplayName("AuthorizationTest-Admin")
    @Nested
    class AuthorizationTestForOrderController {

        @DisplayName("Admin should have access")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamAdmin")
        public void testGetAllOrdersAccessSuccessForAdmin(String user, String pwd) throws Exception {
            mockMvc.perform(get(OrdersController.ORDER_CONTROLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }

        @DisplayName("Customer should have access (Forbidden)")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamCustomer")
        public void testGetAllOrdersAccessForbiddenForCustomer(String user, String pwd) throws Exception {
            mockMvc.perform(get(OrdersController.ORDER_CONTROLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }

        @DisplayName("User should have access (Forbidden)")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamUser")
        public void testGetAllOrdersAccessForbiddenForUser(String user, String pwd) throws Exception {
            mockMvc.perform(get(OrdersController.ORDER_CONTROLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }

        @DisplayName("All Users should have access (Unauthorized)")
        @Test
        public void testGetAllOrdersHttpBasicNotAuth() throws Exception {
            mockMvc.perform(get(OrdersController.ORDER_CONTROLER_BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized());
        }
    }

    @DisplayName("AuthorizationTest-Customer")
    @Nested
    class AuthorizationTestFromUserRole {

        @DisplayName("Admin should have access")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamAdmin")
        public void testGetAllCustomersAccessSuccessForAdmin(String user, String pwd) throws Exception {
            mockMvc.perform(get(CustomerController.CUSTOMER_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }

        @DisplayName("Customer should have access")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamCustomer")
        public void testGetAllCustomersAccessSuccessForCustomer(String user, String pwd) throws Exception {
            mockMvc.perform(get(CustomerController.CUSTOMER_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful());
        }

        @DisplayName("User should have access (Forbidden)")
        @ParameterizedTest(name = "#{index} with [{arguments}]")
        @MethodSource("com.standard.coffeeShop.controller.security.BaseIT#getStreamUser")
        public void testGetAllCustomersForbiddenForForUser(String user, String pwd) throws Exception {
            mockMvc.perform(get(CustomerController.CUSTOMER_CONTROLLER_BASE_URL)
                            .with(httpBasic(user, pwd))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }

        @DisplayName("All Users should have access (Unauthorized)")
        @Test
        public void testGetAllCustomersHttpBasicNotAuth() throws Exception {
            mockMvc.perform(get(CustomerController.CUSTOMER_CONTROLLER_BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized());
        }
    }


}
