package com.phincon.order.orchestration.service;

import com.phincon.order.orchestration.model.Orders;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrchestrationStepImplTest {
    @Mock
    JmsTemplate jmsTemplate;
    @InjectMocks
    private OrchestrationStepImpl orchestrationStep;

    @Test
    void crmTest() {
        Orders order = new Orders();
        Mockito.doNothing().when(jmsTemplate).convertAndSend("queue.test", order);
        Mono<Orders> result = orchestrationStep.crm(order);
        assertEquals("Success CRM", result.block().getStatus());
    }

    @Test
    void notif() {
        Orders order = new Orders();
        Mockito.doNothing().when(jmsTemplate).convertAndSend("queue.test", order);
        Mono<Orders> result = orchestrationStep.notif(order);
        assertEquals("Success Notification", result.block().getStatus());
    }

    @Test
    void complete() {
        Orders order = new Orders();
        Mockito.doNothing().when(jmsTemplate).convertAndSend("queue.test", order);
        Mono<Orders> result = orchestrationStep.complete(order);
        assertEquals("Completed", result.block().getStatus());
    }

    @Test
    void failed() {
        Orders order = new Orders();
        Mockito.doNothing().when(jmsTemplate).convertAndSend("queue.test", order);
        Mono<Orders> result = orchestrationStep.failed(order);
        assertEquals("Failed", result.block().getStatus());
    }
}
