package org.iesalixar.daw2.danielquintero.crudpartediario.mappers;

import org.iesalixar.daw2.danielquintero.crudpartediario.dtos.EncargadoDTO;
import org.iesalixar.daw2.danielquintero.crudpartediario.entities.Encargado;
import org.springframework.stereotype.Component;

@Component
public class EncargadoMapper {
    public EncargadoDTO toDTO(Encargado encargado) {
        if (encargado == null) return null;
        return new EncargadoDTO(encargado.getId(), encargado.getNombre(), encargado.getEmail(), encargado.getTelefono());
    }
}
