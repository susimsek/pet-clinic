package com.web;

import com.model.Owner;
import com.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PetClinicEditOwnerController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(value = "/owners/update/{id}",method = RequestMethod.GET)
    public String loadOwner(@PathVariable long id, ModelMap modelMap){
        Owner owner=petClinicService.findOwner(id);
        modelMap.put("owner",owner);
        return "editOwner";
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/owners/update/{id}")
    public String handleFormSubmit(@ModelAttribute @Valid Owner owner, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "newOwner";
        }
        petClinicService.updateOwner(owner);
        redirectAttributes.addFlashAttribute("message","Owner updated with id :"+owner.getId());
        return "redirect:/owners";
    }

}
