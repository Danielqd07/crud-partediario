package org.iesalixar.daw2.danielquintero.crudpartediario.mappers;

import org.iesalixar.daw2.danielquintero.crudpartediario.dtos.ParteDiarioDTO;
import org.iesalixar.daw2.danielquintero.crudpartediario.entities.Encargado;
import org.iesalixar.daw2.danielquintero.crudpartediario.entities.ParteDiario;
import org.springframework.stereotype.Component;

@Component
public class ParteDiarioMapper {
    public ParteDiarioDTO toDTO(ParteDiario parte) {
        if (parte == null) return null;
        Long encargadoId = parte.getEncargado() != null ? parte.getEncargado().getId() : null;
        String encargadoNombre = parte.getEncargado() != null ? parte.getEncargado().getNombre() : null;
        return new ParteDiarioDTO(
                parte.getId(), parte.getFecha(), parte.getTextoDictado(), parte.getEstado(), encargadoId, encargadoNombre
        );
    }

    public ParteDiario toEntity(ParteDiarioDTO dto, Encargado encargado) {
        ParteDiario parte = new ParteDiario();
        parte.setId(dto.getId());
        parte.setFecha(dto.getFecha());
        parte.setTextoDictado(dto.getTextoDictado());
        parte.setEstado(dto.getEstado());
        parte.setEncargado(encargado);
        return parte;
    }

    public void updateEntity(ParteDiario entity, ParteDiarioDTO dto, Encargado encargado) {
        entity.setFecha(dto.getFecha());
        entity.setTextoDictado(dto.getTextoDictado());
        entity.setEstado(dto.getEstado());
        entity.setEncargado(encargado);
    }
}
