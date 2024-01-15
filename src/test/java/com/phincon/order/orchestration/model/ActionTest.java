package com.phincon.order.orchestration.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActionTest {

    @Test
    void testActionConstructor() {
        Action action = new Action("1", "action");
        assertEquals("1", action.getId());
        assertEquals("action", action.getAction());
    }

    @Test
    void testActionSetter() {
        Action action = new Action();
        action.setId("1");
        action.setAction("action");
        assertEquals("1", action.getId());
        assertEquals("action", action.getAction());
    }

}