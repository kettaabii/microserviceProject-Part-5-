package TacheService.model;


import TacheService.enums.StatusTache;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Getter
@Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "taches")
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate dateDebutTache;

    private LocalDate dateFinTache;

    @Column(name = "project_Id", nullable = false)
    private Long projectId ;

    @Enumerated(EnumType.STRING)
    private StatusTache status;

    @Column(name = "employee_Id", nullable = false)
    private Long assignedEmployee;

}
