package org.iesalixar.daw2.danielquintero.crudpartediario.services.impl;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.danielquintero.crudpartediario.daos.EncargadoDAO;
import org.iesalixar.daw2.danielquintero.crudpartediario.dtos.EncargadoDTO;
import org.iesalixar.daw2.danielquintero.crudpartediario.exceptions.ResourceNotFoundException;
import org.iesalixar.daw2.danielquintero.crudpartediario.mappers.EncargadoMapper;
import org.iesalixar.daw2.danielquintero.crudpartediario.services.EncargadoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EncargadoServiceImpl implements EncargadoService {
    private final EncargadoDAO encargadoDAO;
    private final EncargadoMapper encargadoMapper;

    @Override
    public List<EncargadoDTO> findAll() {
        return encargadoDAO.findAll().stream().map(encargadoMapper::toDTO).toList();
    }

    @Override
    public EncargadoDTO findById(Long id) {
        return encargadoDAO.findById(id)
                .map(encargadoMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el encargado con id " + id));
    }
}
