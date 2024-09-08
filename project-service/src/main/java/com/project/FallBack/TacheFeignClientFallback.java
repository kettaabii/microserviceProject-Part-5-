package com.project.FallBack;

import com.project.FeignClient.TacheFeignClient;
import com.project.model.Tache;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

public class TacheFeignClientFallback implements TacheFeignClient {
    @Override
    public ResponseEntity<List<Tache>> getAllTaches(int projectId) {

        return ResponseEntity.ok(Collections.emptyList());
    }
}
