package com.web;

import com.model.Owner;
import com.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    public String handleFormSubmit(@ModelAttribute @Valid Owner owner, BindingResult bindingResult, RedirectAttributes redirectAttributes){
       if(bindingResult.hasErrors()){
           return "newOwner";
       }
        petClinicService.createOwner(owner);
       redirectAttributes.addFlashAttribute("message","Owner created with id :"+owner.getId());
        return "redirect:/owners";
    }
}
