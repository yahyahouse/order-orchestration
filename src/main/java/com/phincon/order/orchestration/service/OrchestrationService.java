package com.phincon.order.orchestration.service;

import com.phincon.order.orchestration.model.Steps;
import reactor.core.publisher.Flux;

public interface OrchestrationService {
    Flux<Steps> getAction(String actionId);
}
