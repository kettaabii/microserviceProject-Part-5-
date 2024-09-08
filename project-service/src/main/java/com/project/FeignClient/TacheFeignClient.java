package com.project.FeignClient;


import com.project.FallBack.TacheFeignClientFallback;
import com.project.model.Tache;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@FeignClient(name="TACHESERVICE",fallback = TacheFeignClientFallback.class)
public interface TacheFeignClient {

    @GetMapping("/taches/All/{projectId}")
    ResponseEntity<List<Tache>> getAllTaches(@PathVariable("projectId") int projectId);



}
