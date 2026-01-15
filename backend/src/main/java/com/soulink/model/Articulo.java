package com.soulink.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "articulos")
@Getter
@Setter
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo")
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String contenido;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "id_rol")
    private Long idRol; // puedes mapear a Rol si quieres relación
}
