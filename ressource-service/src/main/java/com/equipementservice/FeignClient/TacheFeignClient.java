package com.equipementservice.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TACHESERVICE")
public interface TacheFeignClient {


    @GetMapping("/taches/{id}/exists")
    Boolean exists(@PathVariable Long id);


}
