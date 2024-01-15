package com.phincon.order.orchestration.repository;

import com.phincon.order.orchestration.model.Steps;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OrchestrationRepository extends R2dbcRepository<Steps, String> {

    @Query("SELECT * FROM steps WHERE action_id = :actionId order by priority ")
    Flux<Steps> findByActionId(String actionId);

}
