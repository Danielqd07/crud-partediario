package org.iesalixar.daw2.danielquintero.crudpartediario.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesalixar.daw2.danielquintero.crudpartediario.entities.ParteDiario;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ParteDiarioDAOImpl implements ParteDiarioDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ParteDiario> findAll() {
        return entityManager.createQuery(
                "SELECT p FROM ParteDiario p JOIN FETCH p.encargado ORDER BY p.fecha DESC, p.id DESC",
                ParteDiario.class
        ).getResultList();
    }

    @Override
    public Optional<ParteDiario> findById(Long id) {
        return entityManager.createQuery(
                "SELECT p FROM ParteDiario p JOIN FETCH p.encargado WHERE p.id = :id",
                ParteDiario.class
        ).setParameter("id", id).getResultStream().findFirst();
    }

    @Override
    public ParteDiario save(ParteDiario parteDiario) {
        entityManager.persist(parteDiario);
        return parteDiario;
    }

    @Override
    public ParteDiario update(ParteDiario parteDiario) {
        return entityManager.merge(parteDiario);
    }

    @Override
    public void deleteById(Long id) {
        ParteDiario parteDiario = entityManager.find(ParteDiario.class, id);
        if (parteDiario != null) {
            entityManager.remove(parteDiario);
        }
    }
}
