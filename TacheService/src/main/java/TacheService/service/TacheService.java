package TacheService.service;


import TacheService.FeignClient.ProjectClient;
import TacheService.model.Tache;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import TacheService.repository.TacheRepository;

import java.util.List;

@Service @RequiredArgsConstructor
public class TacheService {


    private final TacheRepository tacheRepository;
    private final ProjectClient projectClient;




    public ResponseEntity<List<Tache>> getAllTasks() {
         List<Tache> tasks =tacheRepository.findAll();
         if (tasks.isEmpty()) {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<List<Tache>> getTasksForProject(Long projectId) {
        Boolean existProject = projectClient.exists(projectId);
        if (existProject) {
            List<Tache> tasks = tacheRepository.findAllByProjectId(projectId);
            if (tasks.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(tasks);
        }
        else {
            return ResponseEntity.notFound().build();
        }


    }

    public ResponseEntity<Tache> AddNewTask(Tache tache) {
        Boolean existProject = projectClient.exists(tache.getProjectId());
        if (existProject) {
            tacheRepository.save(tache);
            return ResponseEntity.ok(tache);
        }else {
            return ResponseEntity.notFound().build();
        }
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
