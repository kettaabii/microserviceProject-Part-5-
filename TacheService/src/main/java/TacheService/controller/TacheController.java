package TacheService.controller;


import TacheService.model.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TacheService.service.TacheService;

import java.util.List;


@RestController
@RequestMapping("/tache")
public class TacheController {
    @Autowired
    private TacheService tacheService;


    @GetMapping
    public ResponseEntity<List<Tache>> getAllTasks() {
       return tacheService.getAllTasks();
    }



    @GetMapping("All/{projectId}")
    public ResponseEntity<List<Tache>> getTachesByProjectId(@PathVariable("projectId") Long projectId) {
        return tacheService.getTasksForProject(projectId);
    }


    @PostMapping
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        return tacheService.AddNewTask(tache);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tache> getTaskById(@PathVariable("id") Long id ){
        return tacheService.getTask(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Tache> updateTask(@PathVariable("id") Long id, @RequestBody Tache tache) {
        return tacheService.update(id,tache);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Tache> deleteTask(@PathVariable("id") Long id) {
       return tacheService.deleteTask(id);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> projectExists(@PathVariable("id") Long id) {
       return tacheService.tacheExist(id);
    }



}
