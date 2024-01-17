package com.phincon.order.orchestration.service;

import com.phincon.order.orchestration.model.Orders;
import com.phincon.order.orchestration.model.Steps;
import com.phincon.order.orchestration.repository.OrchestrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrchestrationServiceImpl implements OrchestrationService {
    @Autowired
    OrchestrationRepository orchestrationRepository;


    @Autowired
    JmsTemplate jmsTemplate;

    Mono<String> status = Mono.just("Success");

    @JmsListener(destination = "queue.status")
    public void status(Message<String> statusMessage) {
        log.info("Status received: " + statusMessage.getPayload());
        status = Mono.just(statusMessage.getPayload());
        log.info("Status: " + status.block());
    }


    @JmsListener(destination = "queue.order")
    public void processOrder(Message<Orders> ordersMessage) {
        ordersMessage.getPayload().setStatus(status.block());
        log.info("Order received: " + ordersMessage.getPayload());
        Flux<Steps> steps = getAction(ordersMessage.getPayload().getActionId());
        steps.subscribe(x -> status.filter(s -> s.equals("Success")).subscribe(
                s -> processStep(x, ordersMessage.getPayload()).subscribe()
        ));
    }

    Mono<Void> processStep(Steps step, Orders order) {
        if (step.getQueue().equals("queue.complete")) {
            order.setStatus("Completed");
            send(step.getQueue(), order).subscribe();
        }
        send(step.getQueue(), order).subscribe();
        return Mono.empty();
    }

    @Override
    public Flux<Steps> getAction(String actionId) {
        return orchestrationRepository.findByActionId(actionId);
    }

    public Mono<Orders> send(String queue, Orders order) {
        log.info("Order Process: " + order.getStatus());
        jmsTemplate.convertAndSend(queue, order);
        return Mono.just(order);
    }


}
