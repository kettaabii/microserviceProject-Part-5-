package TacheService.repository;

import TacheService.model.Tache;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories(basePackages = "path.to.repository")
public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findAllByProjectId(Long projectId);



}
