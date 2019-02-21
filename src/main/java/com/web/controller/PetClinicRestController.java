package com.web.controller;

import com.exception.InternalServerException;
import com.exception.OwnerNotFoundException;
import com.model.Owner;
import com.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(method = RequestMethod.GET,value = "/owners")
    public ResponseEntity<List<Owner>> getOwners(){
        List<Owner> owners=petClinicService.findOwners();
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/owner")
    public ResponseEntity<List<Owner>> getOwner(@RequestParam("ln") String lastName){
        List<Owner> owners=petClinicService.findOwners(lastName);
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/owner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id){
        try {
            Owner owner=petClinicService.findOwner(id);
            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            throw new InternalServerException(e);
        }


    }

    @RequestMapping(method = RequestMethod.POST,value = "/owner")
    public ResponseEntity<URI> createOwner(@RequestBody Owner owner){
        try {
            petClinicService.createOwner(owner);
            Long id=owner.getId();
            URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            throw new InternalServerException(e);
        }



    }

    @RequestMapping(method = RequestMethod.PUT,value = "/owner/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id,@RequestBody Owner ownerRequest){
        try {
            Owner owner=petClinicService.findOwner(id);
            owner.setFirstName(ownerRequest.getFirstName());
            owner.setLastName(ownerRequest.getLastName());
            petClinicService.updateOwner(owner);
            return ResponseEntity.ok().build();

        } catch (OwnerNotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            throw new InternalServerException(e);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id){
        try {
            petClinicService.deleteOwner(id);
            return ResponseEntity.ok().build();

        } catch (OwnerNotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            throw new InternalServerException(e);
        }

    }

    @RequestMapping(method = RequestMethod.GET,value = "/owner/{id}",produces = "application/json")
    public ResponseEntity<?> getOwnerAsHateoasReource(@PathVariable("id") long id){
        try {
            Owner owner=petClinicService.findOwner(id);
            Link self= ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/"+id).withSelfRel();
            Link create= ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner").withRel("create");
            Link update= ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/"+id).withRel("update");
            Link delete= ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/"+id).withRel("delete");
            Resource<Owner> resource=new Resource<>(owner,self,create,update,delete);
            return ResponseEntity.ok(resource);

        } catch (OwnerNotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            throw new InternalServerException(e);
        }
    }




}
