package org.iesalixar.daw2.danielquintero.crudpartediario.services.impl;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.danielquintero.crudpartediario.daos.EncargadoDAO;
import org.iesalixar.daw2.danielquintero.crudpartediario.daos.ParteDiarioDAO;
import org.iesalixar.daw2.danielquintero.crudpartediario.dtos.ParteDiarioDTO;
import org.iesalixar.daw2.danielquintero.crudpartediario.entities.Encargado;
import org.iesalixar.daw2.danielquintero.crudpartediario.entities.ParteDiario;
import org.iesalixar.daw2.danielquintero.crudpartediario.exceptions.InvalidParteDiarioException;
import org.iesalixar.daw2.danielquintero.crudpartediario.exceptions.ResourceNotFoundException;
import org.iesalixar.daw2.danielquintero.crudpartediario.mappers.ParteDiarioMapper;
import org.iesalixar.daw2.danielquintero.crudpartediario.services.ParteDiarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParteDiarioServiceImpl implements ParteDiarioService {
    private static final Set<String> ESTADOS_VALIDOS = Set.of("PENDIENTE", "REVISADO", "FINALIZADO");

    private final ParteDiarioDAO parteDiarioDAO;
    private final EncargadoDAO encargadoDAO;
    private final ParteDiarioMapper parteDiarioMapper;

    @Override
    public List<ParteDiarioDTO> findAll() {
        return parteDiarioDAO.findAll().stream().map(parteDiarioMapper::toDTO).toList();
    }

    @Override
    public ParteDiarioDTO findById(Long id) {
        return parteDiarioDAO.findById(id)
                .map(parteDiarioMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el parte diario con id " + id));
    }

    @Override
    @Transactional
    public ParteDiarioDTO create(ParteDiarioDTO dto) {
        validarReglas(dto);
        Encargado encargado = buscarEncargado(dto.getEncargadoId());
        ParteDiario parte = parteDiarioMapper.toEntity(dto, encargado);
        parte.setId(null);
        return parteDiarioMapper.toDTO(parteDiarioDAO.save(parte));
    }

    @Override
    @Transactional
    public ParteDiarioDTO update(Long id, ParteDiarioDTO dto) {
        validarReglas(dto);
        ParteDiario parte = parteDiarioDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el parte diario con id " + id));
        Encargado encargado = buscarEncargado(dto.getEncargadoId());
        parteDiarioMapper.updateEntity(parte, dto, encargado);
        return parteDiarioMapper.toDTO(parteDiarioDAO.update(parte));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (parteDiarioDAO.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("No existe el parte diario con id " + id);
        }
        parteDiarioDAO.deleteById(id);
    }

    private Encargado buscarEncargado(Long encargadoId) {
        return encargadoDAO.findById(encargadoId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el encargado con id " + encargadoId));
    }

    private void validarReglas(ParteDiarioDTO dto) {
        if (dto.getFecha() != null && dto.getFecha().isAfter(LocalDate.now())) {
            throw new InvalidParteDiarioException("La fecha del parte no puede ser posterior a hoy.");
        }
        if (dto.getEstado() != null && !ESTADOS_VALIDOS.contains(dto.getEstado().toUpperCase())) {
            throw new InvalidParteDiarioException("El estado debe ser PENDIENTE, REVISADO o FINALIZADO.");
        }
        if (dto.getEstado() != null) dto.setEstado(dto.getEstado().toUpperCase());
    }
}
