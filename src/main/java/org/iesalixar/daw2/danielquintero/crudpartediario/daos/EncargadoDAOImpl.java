package org.iesalixar.daw2.danielquintero.crudpartediario.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesalixar.daw2.danielquintero.crudpartediario.entities.Encargado;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class EncargadoDAOImpl implements EncargadoDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Encargado> findAll() {
        return entityManager.createQuery("SELECT e FROM Encargado e ORDER BY e.nombre", Encargado.class)
                .getResultList();
    }

    @Override
    public Optional<Encargado> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Encargado.class, id));
    }
}
