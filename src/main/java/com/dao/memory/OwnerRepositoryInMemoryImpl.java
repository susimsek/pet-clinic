package com.dao.memory;

import com.dao.OwnerRepository;
import com.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OwnerRepositoryInMemoryImpl implements OwnerRepository {

    private Map<Long,Owner> ownersMap=new HashMap<>();

    public OwnerRepositoryInMemoryImpl() {
        Owner owner1=new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Kenan");
        owner1.setLastName("Sevindik");

        Owner owner2=new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Şuayb");
        owner2.setLastName("Şimşek");

        Owner owner3=new Owner();
        owner3.setId(3L);
        owner3.setFirstName("Hasan");
        owner3.setLastName("Sert");

        ownersMap.put(owner1.getId(),owner1);
        ownersMap.put(owner2.getId(),owner2);
        ownersMap.put(owner3.getId(),owner3);

    }

    @Override
    public List<Owner> findAll() {
        return new ArrayList<>(ownersMap.values());
    }

    @Override
    public Owner findById(Long id) {
        return ownersMap.get(id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownersMap.values().stream().filter(o->o.getLastName().equals(lastName)).collect(Collectors.toList());
    }

    @Override
    public void create(Owner owner) {
        owner.setId(new Date().getTime());
        ownersMap.put(owner.getId(),owner);
    }

    @Override
    public Owner update(Owner owner) {
        ownersMap.replace(owner.getId(),owner);
        return owner;
    }

    @Override
    public void delete(Long id) {
        ownersMap.remove(id);

    }
}
