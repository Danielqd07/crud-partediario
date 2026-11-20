package org.iesalixar.daw2.danielquintero.crudpartediario.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "encargados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Encargado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "telefono", nullable = false, length = 30)
    private String telefono;
}
