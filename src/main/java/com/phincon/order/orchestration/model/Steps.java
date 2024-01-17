package com.phincon.order.orchestration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
public class Steps {
    @Id
    String id;
    String actionId;
    String steps;
    String queue;
    Integer priority;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Steps(String id, String actionId, String steps, String queue, Integer priority) {
        this.id = id;
        this.actionId = actionId;
        this.steps = steps;
        this.queue = queue;
        this.priority = priority;
    }

    public Steps() {
    }
}
