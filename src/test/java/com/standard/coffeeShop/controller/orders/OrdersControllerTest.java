package com.standard.coffeeShop.controller.orders;


import com.standard.coffeeShop.controller.AbstractRestControllerTest;
import com.standard.coffeeShop.repository.entity.StatusOrderEnum;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest
public class OrdersControllerTest extends AbstractRestControllerTest {

    @BeforeEach
    void setUp() {
        // set required methods
        orderRequestDto = setUpOrderDto();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        orderRequestDto = setUpOrderWithItemsDto();
        //   when(orderRequestService.getAll()).thenReturn(Arrays.asList(orderRequestDto));
        mockMvc.perform(get(OrdersController.ORDER_CONTROLLER_BASE_URL)
                        .with(httpBasic("admin", "spring"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)));
        // TODO: add more fields
    }

    @Test
    public void testGetOrderById() throws Exception {
        orderRequestDto = setUpOrderWithItemsDto();
        //       when(orderRequestService.getById(1)).thenReturn(orderRequestDto);
        mockMvc.perform(get(OrdersController.ORDER_CONTROLLER_BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
        // TODO: add more fields
    }

    @Test
    void testOpenOrder() throws Exception {
        mockMvc.perform(post(OrdersController.ORDER_CONTROLLER_BASE_URL + "/open")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void testAddProduct() throws Exception {
        orderRequestDto = setUpOrderWithItemsDto();
        mockMvc.perform(put(OrdersController.ORDER_CONTROLLER_BASE_URL + "/1/add/1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderItems", hasSize(1)))
                .andExpect(jsonPath("$.orderItems[0].product.id", hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(StatusOrderEnum.OPEN.toString())));
        //TODO: add moer fields
    }

    @Test
    void testRemoveProduct() throws Exception {
        mockMvc.perform(put(OrdersController.ORDER_CONTROLLER_BASE_URL + "/1/remove/1").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderItems", hasSize(0)))
                .andExpect(jsonPath("$.status", is(StatusOrderEnum.OPEN.toString())));
        //TODO: add more fields
    }


    @Test
    void testPrintOrder() throws Exception {
        printReceiptDto = setUpPrintReceiptDto();
        mockMvc.perform(get(OrdersController.ORDER_CONTROLLER_BASE_URL + "/1/receipt").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.receiptItems", hasSize(1)))
                .andExpect(jsonPath("$.total", equalTo(12.0)));

    }

    @Test
    public void testDelete() throws Exception {
        orderRequestDto = setUpOrderWithItemsDto();
        orderRequestDto.setStatus(StatusOrderEnum.DELETE.toString());
        mockMvc.perform(put(OrdersController.ORDER_CONTROLLER_BASE_URL + "/1/delete").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusOrderEnum.DELETE.toString())));
    }


}
