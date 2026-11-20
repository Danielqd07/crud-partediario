package org.iesalixar.daw2.danielquintero.crudpartediario.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncargadoDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
}
