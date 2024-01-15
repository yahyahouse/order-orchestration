package com.phincon.order.orchestration.service;

import com.phincon.order.orchestration.model.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrchestrationStepImpl implements OrchestrationStep {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${step.step1-queue}")
    private String crmRegister;

    @Value("${step.step2-queue}")
    private String notifStep;

    @Value("${step.step3-queue}")
    private String stepComplete;
    @Value("${step.failed-queue}")
    private String ordersFailed;

    @Override
    public Mono<Orders> crm(Orders order) {
        try {
            order.setStatus("Success CRM");
            log.info("Order process CRM " + order.getStatus());
            jmsTemplate.convertAndSend(crmRegister, order);
            return Mono.just(order);
        } catch (Exception e) {
            log.info("Order Failed " + order.getStatus());
            return failed(order);
        }
    }

    @Override
    public Mono<Orders> notif(Orders order) {
        try {
            order.setStatus("Success Notification");
            log.info("Order process Notification " + order.getStatus());
            jmsTemplate.convertAndSend(notifStep, order);
            return Mono.just(order);
        } catch (Exception e) {
            log.info("Order Failed " + order.getStatus());
            return failed(order);
        }
    }

    @Override
    public Mono<Orders> complete(Orders order) {
        order.setStatus("Completed");
        log.info("Order completed: " + order.getStatus());
        jmsTemplate.convertAndSend(stepComplete, order);
        return Mono.just(order);
    }

    public Mono<Orders> failed(Orders order) {
        order.setStatus("Failed");
        log.info("Order failed: " + order.getStatus());
        jmsTemplate.convertAndSend(ordersFailed, order);
        return Mono.just(order);
    }
}
