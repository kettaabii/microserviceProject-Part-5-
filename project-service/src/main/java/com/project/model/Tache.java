package com.project.model;

import com.project.enums.StatusTache;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
public class Tache {
    private Long id;

    private String title;

    private String description;

    private LocalDate dateDebutTache;

    private LocalDate dateFinTache;

    private StatusTache status;
}
