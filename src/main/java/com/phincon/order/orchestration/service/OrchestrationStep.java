package com.phincon.order.orchestration.service;

import com.phincon.order.orchestration.model.Orders;
import reactor.core.publisher.Mono;

public interface OrchestrationStep {
    Mono<Orders> crm(Orders order);
    Mono<Orders> notif(Orders order);
    Mono<Orders> complete(Orders order);
    Mono<Orders> failed(Orders order);
}
