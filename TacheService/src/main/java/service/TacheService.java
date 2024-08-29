package service;


import model.Tache;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.TacheRepository;

import java.util.List;

@Service
public class TacheService {

    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    private final TacheRepository tacheRepository;

    public ResponseEntity<List<Tache>> getAllTasks() {
         List<Tache> tasks =tacheRepository.findAll();
         if (tasks.isEmpty()) {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<List<Tache>> getTasksForProject(int projectId) {
        List<Tache> Tasks = tacheRepository.findAllByProjectId(projectId);
        if (Tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Tasks);
    }

    public ResponseEntity<Tache> AddNewTask(Tache tache) {
        tacheRepository.save(tache);
        return ResponseEntity.ok(tache);
    }

    public ResponseEntity<Tache> getTask(Long id) {
        tacheRepository.findById(id).ifPresent(ResponseEntity::ok);
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Tache> update(Long id, Tache tache) {
       return tacheRepository.findById(id).map(existingTache ->{
            existingTache.setDateDebutTache(tache.getDateDebutTache());
            existingTache.setDateFinTache(tache.getDateFinTache());
            existingTache.setTitle(tache.getTitle());
            existingTache.setDescription(tache.getDescription());
            existingTache.setAssignedEmployee(tache.getAssignedEmployee());
            existingTache.setProjectId(tache.getProjectId());
            existingTache.setStatus(tache.getStatus());
            tacheRepository.save(existingTache);
            return ResponseEntity.ok(existingTache);
        }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<Tache> deleteTask(Long id) {
        tacheRepository.deleteById(id);
        return ResponseEntity.ok(tacheRepository.findById(id).orElse(null));
    }

    public ResponseEntity<Boolean> tacheExist(Long id) {
        tacheRepository.findById(id).ifPresent(ResponseEntity::ok);
      return   ResponseEntity.ok(true);
    }
}
