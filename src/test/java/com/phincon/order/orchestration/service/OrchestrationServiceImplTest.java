package com.phincon.order.orchestration.service;

import com.phincon.order.orchestration.model.Steps;
import com.phincon.order.orchestration.repository.OrchestrationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrchestrationServiceImplTest {
    @Mock
    JmsTemplate jmsTemplate;

    @Autowired
    OrchestrationServiceImpl orchestrationService;

    @Mock
    OrchestrationRepository orchestrationRepository;

    @Test
    void testProcessOrder() {

    }

    @Test
    void testGetAction() {

        Steps steps = new Steps();
        steps.setId("1");
        steps.setActionId("1");
        steps.setSteps("step");
        steps.setQueue("s");
        steps.setPriority(1);
        when(orchestrationRepository.findByActionId(any(String.class))).thenReturn(Flux.just(steps));
        Flux<Steps> result = orchestrationService.getAction("1");
        result.subscribe(steps1 -> {
            assertEquals("1", steps1.getId());
            assertEquals("1", steps1.getActionId());
            assertEquals("step", steps1.getSteps());
            assertEquals("s", steps1.getQueue());
            assertEquals(1, steps1.getPriority());
        });

    }

    @Test
    void testSend() {
    }

}