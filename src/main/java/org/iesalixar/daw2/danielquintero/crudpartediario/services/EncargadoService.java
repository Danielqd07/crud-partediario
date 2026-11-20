package org.iesalixar.daw2.danielquintero.crudpartediario.services;

import org.iesalixar.daw2.danielquintero.crudpartediario.dtos.EncargadoDTO;
import java.util.List;

public interface EncargadoService {
    List<EncargadoDTO> findAll();
    EncargadoDTO findById(Long id);
}
