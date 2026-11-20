package org.iesalixar.daw2.danielquintero.crudpartediario.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "partes_diarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParteDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "texto_dictado", nullable = false, length = 500)
    private String textoDictado;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "encargado_id", nullable = false)
    private Encargado encargado;
}
