package com.example.Custumer_Service.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CUSTUMER")
public class CustumerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_client;

    @Column(name = "NOM")
    private String nom_client;

    @Column(name = "PRENOM")
    private String prenom_client;

    @Column(name = "ADRESSE")
    private String add_client;

    public CustumerModel() {
    }

    public CustumerModel(long id_client, String nom_client, String prenom_client, String add_client) {
        this.id_client = id_client;
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.add_client = add_client;
    }
}
