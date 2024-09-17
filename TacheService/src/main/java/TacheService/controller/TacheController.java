package TacheService.controller;


import TacheService.model.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import TacheService.service.TacheService;

import java.util.List;


@RestController
@RequestMapping("/taches")
public class TacheController {
    @Autowired
    private TacheService tacheService;


    @GetMapping
    public ResponseEntity<List<Tache>> getAllTasks() {
       return tacheService.getAllTasks();
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("All/{projectId}")
    public ResponseEntity<List<Tache>> getTachesByProjectId(@PathVariable Long projectId) {
        return tacheService.getTasksForProject(projectId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        return tacheService.AddNewTask(tache);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<Tache> getTaskById(@PathVariable("id") Long id ){
        return tacheService.getTask(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update/{id}")
    public ResponseEntity<Tache> updateTask(@PathVariable("id") Long id, @RequestBody Tache tache) {
        return tacheService.update(id,tache);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Tache> deleteTask(@PathVariable("id") Long id) {
       return tacheService.deleteTask(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> TacheExists(@PathVariable("id") Long id) {
       return tacheService.tacheExist(id);
    }



}
