package com.phincon.order.orchestration.model;

import lombok.Data;

@Data
public class Orders {
    String id;
    String productId;
    String status;
    Long price;
    String actionId;
}
