package controller;


import model.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import service.TacheService;

import java.util.List;

@RestController
@RequestMapping("api/Tache")
public class TacheController {
    @Autowired
    private TacheService tacheService;

    @GetMapping
    public ResponseEntity<List<Tache>> getAllTasks() {
       return tacheService.getAllTasks();
    }

    @GetMapping("Taches/{projectId}")
    public ResponseEntity<List<Tache>> getTachesByProjectId(@PathVariable("projectId") int projectId) {
        return tacheService.getTasksForProject(projectId);
    }

    @PostMapping
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        return tacheService.AddNewTask(tache);
    }

}
