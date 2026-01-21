package com.booqo.booqo_api.center;

import com.booqo.booqo_api.client.Client;
import com.booqo.booqo_api.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "centers")
@Schema(description = "Representa un centro de trabajo o sede de la clínica")
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "Centro Médico Booqo Norte")
    @NotBlank
    private String name;

    @Schema(example = "B12345678")
    @NotBlank
    private String cif;

    // Relaciones
//    @OneToMany(mappedBy = "center")
//    private List<User> employees;

    //@OneToMany(mappedBy = "center")
    //private List<Client> clients;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

//    public List<User> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<User> employees) {
//        this.employees = employees;
//    }

//    public List<Client> getClients() {
//        return clients;
//    }
//
//    public void setClients(List<Client> clients) {
//        this.clients = clients;
//    }
}