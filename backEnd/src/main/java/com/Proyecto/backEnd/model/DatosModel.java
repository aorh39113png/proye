package com.Proyecto.backEnd.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "datos")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DatosModel {

    @Id
    private Integer codp;

    @Column(length = 10, nullable = false, unique = true)
    private String cedula;

    /*
     * @OneToOne
     * 
     * @MapsId // usa el mismo codp como ID
     * 
     * @JoinColumn(name = "codp")
     * 
     * @JsonBackReference
     * private PersonalModel personal;
     */

    @OneToOne
    @MapsId
    @JoinColumn(name = "codp")
    @JsonBackReference
    private PersonalModel personal;

    // Getters y setters
    public Integer getCodp() {
        return codp;
    }

    public void setCodp(Integer codp) {
        this.codp = codp;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public PersonalModel getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalModel personal) {
        this.personal = personal;
    }
}
