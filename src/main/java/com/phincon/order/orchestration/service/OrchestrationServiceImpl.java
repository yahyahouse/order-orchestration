package com.phincon.order.orchestration.service;

import com.phincon.order.orchestration.model.Orders;
import com.phincon.order.orchestration.model.Steps;
import com.phincon.order.orchestration.repository.OrchestrationRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Service
@Slf4j
public class  OrchestrationServiceImpl implements OrchestrationService {
    @Autowired
    OrchestrationRepository orchestrationRepository;

    @Autowired
    OrchestrationStep orchestrationStep;

    private final Map<String, Consumer<Orders>> stepActions = new ConcurrentHashMap<>();
    Orders orderNew = new Orders();

    private void addStepAction(String stepName, Consumer<Orders> action) {
        stepActions.put(stepName, action);
    }

    @PostConstruct
    private void initializeStepActions() {
        addStepAction("crm", orchestrationStep::crm);
        addStepAction("notif", orchestrationStep::notif);
        addStepAction("complete", orchestrationStep::complete);
        addStepAction("failed", orchestrationStep::failed);
    }

    @JmsListener(destination = "queue.order")
    public void processOrder(Message<Orders> ordersMessage) {
        orderNew = ordersMessage.getPayload();
        log.info("Order received: " + orderNew);
        Flux<Steps> steps = getAction(orderNew.getActionId());
        steps.subscribe(steps1 -> processStep(steps1));
    }

    private void processStep(Steps step) {
        String stepName = step.getSteps();
        if (stepActions.containsKey(stepName)) {
            stepActions.get(stepName).accept(orderNew);
        } else {
            handleUnknownStep(orderNew);
        }
    }

    @Override
    public Flux<Steps> getAction(String actionId) {
        return orchestrationRepository.findByActionId(actionId);
    }

    private void handleUnknownStep(Orders order) {
        log.warn("Unknown step");
        log.info("Order Failed " + order.getStatus());
    }



}
