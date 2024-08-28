package repository;

import model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
}
