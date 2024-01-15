package com.phincon.order.orchestration.service;

import com.phincon.order.orchestration.repository.OrchestrationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

@SpringBootTest
class OrchestrationServiceImplTest {
    @Mock
    JmsTemplate jmsTemplate;

    @InjectMocks
    OrchestrationServiceImpl yourClass;

    @Mock
    OrchestrationRepository orchestrationRepository;

    @Test
    void testProcessOrder() {

    }

}