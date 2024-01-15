package com.phincon.order.orchestration.model;

import lombok.Data;

@Data
public class Notification {
    String id;
    String orderId;
    String status;
}
