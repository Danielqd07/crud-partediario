package org.iesalixar.daw2.danielquintero.crudpartediario.daos;

import org.iesalixar.daw2.danielquintero.crudpartediario.entities.ParteDiario;
import java.util.List;
import java.util.Optional;

public interface ParteDiarioDAO {
    List<ParteDiario> findAll();
    Optional<ParteDiario> findById(Long id);
    ParteDiario save(ParteDiario parteDiario);
    ParteDiario update(ParteDiario parteDiario);
    void deleteById(Long id);
}
