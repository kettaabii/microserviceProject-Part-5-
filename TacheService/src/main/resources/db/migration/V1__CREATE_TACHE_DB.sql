CREATE TABLE taches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    title VARCHAR(255) ,
    description VARCHAR(255),
    date_debut_tache DATETIME,
    date_fin_tache DATETIME ,
    project_Id BIGINT NOT NULL ,
    status ENUM('A_Faire','En_Cours','Terminée') DEFAULT('A_Faire') NOT NULL ,
    employee_Id BIGINT NOT NULL
)