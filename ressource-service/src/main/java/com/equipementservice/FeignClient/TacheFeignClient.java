package com.equipementservice.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TacheService")
public interface TacheFeignClient {


    @GetMapping("/taches/{id}/exist")
    Boolean exists(@PathVariable Long id);


}
