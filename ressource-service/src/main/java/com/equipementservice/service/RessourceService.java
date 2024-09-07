package com.equipementservice.service;


import com.equipementservice.model.Ressource;
import com.equipementservice.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RessourceService {
    @Autowired
    private RessourceRepository ressourceRepository;

    public void addEquipement(Ressource ressource){
        ressourceRepository.save(ressource);
    }

    public ResponseEntity<List<Ressource>> getAllRessources() {
        List<Ressource> ressources = ressourceRepository.findAll();
        if(ressources.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ressources);
    }

    public ResponseEntity<Ressource> updateRessource(Long id, Ressource ressource) {
        Optional<Ressource> ressourceOptional = ressourceRepository.findById(id);
        if(ressourceOptional.isPresent()){
           ressourceOptional.get().setName(ressource.getName());
           ressourceOptional.get().setDescription(ressource.getDescription());
           ressourceOptional.get().setFournisseur(ressource.getFournisseur());
           ressourceRepository.save(ressourceOptional.get());
            return ResponseEntity.ok(ressourceOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Ressource> deleteRessource(Long id) {
        ressourceRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
