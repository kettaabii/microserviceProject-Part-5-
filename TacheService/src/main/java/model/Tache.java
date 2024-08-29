package model;


import enums.StatusTache;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity @Getter
@Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "taches")
public class Tache {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String description;

    private Date dateDebutTache;

    private Date dateFinTache;

    @Column(name = "project_Id", nullable = false)
    private Long ProjectId ;

    private StatusTache status;

    @Column(name = "employee_Id", nullable = false)
    private Long assignedEmployee;

}
