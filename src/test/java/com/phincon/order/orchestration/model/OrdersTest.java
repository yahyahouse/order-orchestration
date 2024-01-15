package com.phincon.order.orchestration.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersTest {

    @Test
    void OrdersTest(){
        Orders orders = new Orders();
        orders.setId("1");
        orders.setProductId("1");
        orders.setPrice(1000L);
        orders.setStatus("status");
        orders.setActionId("1");
        assertEquals("1", orders.getId());
        assertEquals("1", orders.getProductId());
        assertEquals(1000L, orders.getPrice());
        assertEquals("status", orders.getStatus());
        assertEquals("1", orders.getActionId());
    }

}