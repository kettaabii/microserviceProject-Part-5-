package com.equipementservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.equipementservice.model.Ressource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.equipementservice.service.RessourceService;

import java.util.List;
@CrossOrigin ("*")
@RestController
@RequestMapping("/ressources")
@Tag(name = "ressources")
@RequiredArgsConstructor
public class RessourceController {
    @Autowired
    private RessourceService ressourceService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "new ressource")
    public ResponseEntity<Ressource> create(@RequestBody Ressource ressource) {
      ressourceService.addEquipement(ressource);
      return new ResponseEntity<>(ressource, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping
    @Operation(summary = "get All Ressources")
    public ResponseEntity<List<Ressource>> getAll() {
       return ressourceService.getAllRessources();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/ressource/{id}")
    @Operation(summary = "Updte ressource")
    public ResponseEntity<Ressource> update(@RequestBody Ressource ressource, @PathVariable Long id) {
        return ressourceService.updateRessource(id,ressource);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "DELETE ressource")
    public ResponseEntity<Ressource> delete(@PathVariable Long id) {
      return   ressourceService.deleteRessource(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/tache/{tacheId}")
    @Operation(summary = "Find Ressources For Tache")
    public ResponseEntity<List<Ressource>> getTasksByProjectId(@PathVariable Long tacheId)  {
        return ressourceService.getResourcesByTaskId(tacheId);
    }



}
