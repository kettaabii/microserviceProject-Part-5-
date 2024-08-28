package controller;

import model.Equipement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.EquipementService;

@RestController
@RequestMapping("/api/equipements")
public class equipementController {
    @Autowired
    private EquipementService equipementService;

    @PostMapping("/api/equipements/newEquipement")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEquipement(@RequestBody Equipement equipement)
    {
        equipementService.addEquipement(equipement);

    }
}
