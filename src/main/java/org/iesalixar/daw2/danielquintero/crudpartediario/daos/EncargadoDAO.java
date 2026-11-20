package org.iesalixar.daw2.danielquintero.crudpartediario.daos;

import org.iesalixar.daw2.danielquintero.crudpartediario.entities.Encargado;
import java.util.List;
import java.util.Optional;

public interface EncargadoDAO {
    List<Encargado> findAll();
    Optional<Encargado> findById(Long id);
}
