package com.equipementservice.repository;


import com.equipementservice.model.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
    List<Ressource> findAllByTacheId(Long tacheId);

}
