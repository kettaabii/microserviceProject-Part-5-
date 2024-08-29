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
}
