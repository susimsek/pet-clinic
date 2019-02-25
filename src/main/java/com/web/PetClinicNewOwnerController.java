package com.web;

import com.model.Owner;
import com.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PetClinicNewOwnerController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(method = RequestMethod.GET ,value = "/owners/new")
    public String newOwner(){
        return "newOwner";

    }

    @ModelAttribute
    public Owner initModel(){
        return new Owner();
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/owners/new")
    public String handleFormSubmit(@ModelAttribute Owner owner){
        petClinicService.createOwner(owner);
        return "redirect:/owners";
    }
}
