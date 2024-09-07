CREATE TABLE ressource(
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    name varchar(255) NOT NULL ,
    description VARCHAR(255) NOT NULL ,
    image VARCHAR(255) ,

    qty INTEGER ,
    type ENUM('MATERIEL','HUMAINE','AUTRE') NOT NULL ,
    fournisseur VARCHAR(255) NOT NULL ,
    tache_id BIGINT


)
