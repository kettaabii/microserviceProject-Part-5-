package com.equipementservice.service;


import com.equipementservice.FeignClient.TacheFeignClient;
import com.equipementservice.model.Ressource;
import com.equipementservice.repository.RessourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class RessourceService {
    private final TacheFeignClient tacheFeignClient;
    @Autowired
    private RessourceRepository ressourceRepository;

    public ResponseEntity<Ressource> addEquipement(Ressource ressource){

        if(ressource.getTacheId()==null){
            throw new IllegalArgumentException("Task ID must not be null");
        }
        boolean exists = tacheFeignClient.exists(ressource.getTacheId());
        if(exists){
            ressourceRepository.save(ressource);
            return ResponseEntity.ok(ressource);
        }
        else {
            return ResponseEntity.notFound().build();
        }

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

    public ResponseEntity<List<Ressource>> getResourcesByTaskId(Long taskId) {
        Boolean exists = tacheFeignClient.exists(taskId);
        if(exists){
            List<Ressource> ressoures = ressourceRepository.findAllByTacheId(taskId);
            return ResponseEntity.ok(ressoures);

        }
        return ResponseEntity.notFound().build();

    }
}
