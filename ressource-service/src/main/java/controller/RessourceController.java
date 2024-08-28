package controller;

import model.Ressource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RessourceService;

import java.util.List;

@RestController
@RequestMapping("/api/ressources")
public class RessourceController {
    @Autowired
    private RessourceService ressourceService;

    @PostMapping()
    public ResponseEntity<Ressource> create(@RequestBody Ressource ressource) {
      ressourceService.addEquipement(ressource);
      return new ResponseEntity<>(ressource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ressource>> getAll() {
       return ressourceService.getAllRessources();
    }


    @PutMapping("/ressource/{id}")
    public ResponseEntity<Ressource> update(@RequestBody Ressource ressource, @PathVariable Long id) {
        return ressourceService.updateRessource(id,ressource);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Ressource> delete(@PathVariable Long id) {
      return   ressourceService.deleteRessource(id);
    }





}
