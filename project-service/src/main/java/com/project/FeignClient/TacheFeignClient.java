package com.project.FeignClient;


import com.project.FallBack.TacheFeignClientFallback;
import com.project.model.Tache;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@FeignClient(name="TACHESERVICE"/*fallback = TacheFeignClientFallback.class*/)
public interface TacheFeignClient {

    @GetMapping("/taches/All/{projectId}")
    @CircuitBreaker(name="TacheService", fallbackMethod = "getTaskFallback")
    ResponseEntity<List<Tache>> getAllTaches(@PathVariable("projectId") int projectId);

    default ResponseEntity<List<Tache>> getTaskFallback(int projectId, Throwable exception) {
        Tache tache = new Tache();
        tache.setId((long) projectId);
        tache.setDescription("Not available");
        tache.setTitle("Not available");


        return ResponseEntity.ok(Collections.singletonList(tache));
    }

}
