package com.Proyecto.backEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonalConLoginDTO {

    private Integer codp;
    private String nombre;
    private String ap;
    private String am;
    private int estado;
    private String tipo;
    private String foto;

    private String login;
}
