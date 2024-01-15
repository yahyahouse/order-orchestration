package com.phincon.order.orchestration.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StepsTest {

    @Test
    void testStepsConstructor() {
        Steps steps = new Steps("1", "1", "step", 1);
        assertEquals("1", steps.getId());
        assertEquals("1", steps.getActionId());
        assertEquals("step", steps.getSteps());
        assertEquals(1, steps.getPriority());
    }

    @Test
    void testSteps() {
        Steps steps = new Steps();
        steps.setId("1");
        steps.setActionId("1");
        steps.setSteps("step");
        steps.setPriority(1);
        assertEquals("1", steps.getId());
        assertEquals("1", steps.getActionId());
        assertEquals("step", steps.getSteps());
        assertEquals(1, steps.getPriority());
    }
}