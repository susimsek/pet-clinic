package com.service;

import com.dao.OwnerRepository;
import com.dao.PetRepository;
import com.dao.jpa.VetRepository;
import com.exception.OwnerNotFoundException;
import com.exception.VetNotFoundException;
import com.model.Owner;
import com.model.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor =Exception.class)
public class PetClinicServiceImpl implements PetClinicService {

    private OwnerRepository ownerRepository;
    private PetRepository petRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    private VetRepository vetRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Autowired
    public void setVetRepository(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Autowired
    public void setPetRepository(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Secured(value = {"ROLE_USER","ROLE_EDITOR"})
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Owner> findOwners(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Owner findOwner(Long id) throws OwnerNotFoundException {
        Owner owner=ownerRepository.findById(id);
        if(owner==null){
            throw new OwnerNotFoundException("Owner not found with id : "+id);
        }
        return owner;

    }

    @Override
    public void createOwner(Owner owner) {
        ownerRepository.create(owner);
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setFrom("k@s");
        mail.setTo("m@y");
        mail.setSubject("Owner Created!");
        mail.setText("Owne entity with id : "+owner.getId()+" created successfully.");
        javaMailSender.send(mail);
    }

    @Override
    public void updateOwner(Owner owner) {
        ownerRepository.update(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        petRepository.deleteByOwnerId(id);
        ownerRepository.delete(id);
        //if(true) throw new RuntimeException("testing rolback");
    }

    @Override
    public List<Vet> findVets() {
        return vetRepository.findAll();
    }

    @Override
    public Vet findVet(long id) throws VetNotFoundException {
        return vetRepository.findById(id).orElseThrow(()->{return new VetNotFoundException("Vet not found by id : "+id);});
    }
}
