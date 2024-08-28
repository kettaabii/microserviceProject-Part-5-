package service;

import model.Equipement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EquipementRepository;

@Service
public class EquipementService {
    @Autowired
    private EquipementRepository equipementRepository;

    public void addEquipement(Equipement equipement){
        equipementRepository.save(equipement);
    }
}
