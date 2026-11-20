package org.iesalixar.daw2.danielquintero.crudpartediario.services;

import org.iesalixar.daw2.danielquintero.crudpartediario.dtos.ParteDiarioDTO;
import java.util.List;

public interface ParteDiarioService {
    List<ParteDiarioDTO> findAll();
    ParteDiarioDTO findById(Long id);
    ParteDiarioDTO create(ParteDiarioDTO dto);
    ParteDiarioDTO update(Long id, ParteDiarioDTO dto);
    void delete(Long id);
}
