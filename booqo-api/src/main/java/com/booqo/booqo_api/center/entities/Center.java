package com.booqo.booqo_api.center.entities;

import com.booqo.booqo_api.booking.entities.Booking;
import com.booqo.booqo_api.client.entities.Client;
import com.booqo.booqo_api.user.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "centers")
@Schema(description = "Representa un centro de trabajo o sede de la clínica")
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true, length = 20)
    private String cif;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String address;

    @NotBlank
    @Column(nullable = false, length = 15)
    private String phone;

    @NotBlank
    @Email
    @Column(nullable = false, length = 100)
    private String email;

    // Relaciones
    @OneToMany(mappedBy = "center")
    private List<User> employees;

    // Dentro de Center.java
    @OneToMany(mappedBy = "center")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "center")
    private List<Client> clients;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}