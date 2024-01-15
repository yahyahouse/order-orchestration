package com.phincon.order.orchestration.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationTest {

    @Test
    void testNotification() {
        Notification notification = new Notification();
        notification.setId("1");
        notification.setOrderId("1");
        notification.setStatus("status");
        assertEquals("1", notification.getId());
        assertEquals("1", notification.getOrderId());
        assertEquals("status", notification.getStatus());
    }
}