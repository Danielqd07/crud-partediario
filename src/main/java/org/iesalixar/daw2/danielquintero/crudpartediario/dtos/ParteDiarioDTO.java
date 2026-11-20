package org.iesalixar.daw2.danielquintero.crudpartediario.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParteDiarioDTO {
    private Long id;

    @NotNull(message = "{validation.fecha.required}")
    private LocalDate fecha;

    @NotBlank(message = "{validation.textoDictado.required}")
    @Size(max = 500, message = "{validation.textoDictado.size}")
    private String textoDictado;

    @NotBlank(message = "{validation.estado.required}")
    @Size(max = 30, message = "{validation.estado.size}")
    private String estado;

    @NotNull(message = "{validation.encargado.required}")
    private Long encargadoId;

    private String encargadoNombre;
}
