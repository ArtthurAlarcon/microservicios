package com.innova.movies.serie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Table(name = "serie")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo de nombre no debe ser vacío")
    @Column(unique = true)
    private String nombre;

    @NotEmpty(message = "El campo de genero no debe ser vacío")
    private String genero;

    @NotEmpty(message = "El campo de imagen no debe ser vacío")
    private String imagen;

    @NotEmpty(message = "El campo de link no debe ser vacío")
    private String link;

    @Past(message = "La fecha debe ser en formato AAAA-MM-DD y no debe ser posterior al dia de hoy")
    @Temporal(TemporalType.DATE)
    private Date fecha;
}
