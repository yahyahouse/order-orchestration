package com.phincon.order.orchestration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Steps {
    @Id
    String id;
    String actionId;
    String steps;
    Integer priority;
}
