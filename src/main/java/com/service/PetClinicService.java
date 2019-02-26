package com.service;

import com.exception.OwnerNotFoundException;
import com.exception.VetNotFoundException;
import com.model.Owner;
import com.model.Vet;

import java.util.List;

public interface PetClinicService {
    List<Owner> findOwners();
    List<Owner> findOwners(String lastName);
    Owner findOwner(Long id) throws OwnerNotFoundException;
    void createOwner(Owner owner);
    void updateOwner(Owner owner);
    void deleteOwner(Long id);

    List<Vet> findVets();
    Vet findVet(long id) throws VetNotFoundException;
}
