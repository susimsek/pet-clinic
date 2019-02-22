package com.dao.jpa;

import com.dao.PetRepository;
import com.model.Pet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("petRepository")
public class PetRepositoryJpaImpl implements PetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pet findById(Long id) {
        return entityManager.find(Pet.class,id);
    }

    @Override
    public List<Pet> findByOwnerId(Long ownerId) {
        return entityManager.createQuery("from Pet where owner.id = :ownerId")
                .setParameter("ownerId",ownerId).getResultList();
    }

    @Override
    public void create(Pet pet) {
        entityManager.persist(pet);
    }

    @Override
    public Pet update(Pet pet) {
        return entityManager.merge(pet);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Pet.class,id));

    }

    @Override
    public void deleteByOwnerId(Long ownerId) {
        entityManager.createQuery("delete from Pet where owner.id = :ownerId")
                .setParameter("ownerId",ownerId).executeUpdate();

    }
}
