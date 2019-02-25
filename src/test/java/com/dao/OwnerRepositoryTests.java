package com.dao;

import com.model.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class OwnerRepositoryTests {

    @Autowired
    private OwnerRepository ownerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test(expected = PersistenceException.class)
    public void testCreateOwner(){
        Owner owner=new Owner();
        owner.setFirstName(null);
        owner.setLastName(null);
        ownerRepository.create(owner);

        entityManager.flush();
    }


}
