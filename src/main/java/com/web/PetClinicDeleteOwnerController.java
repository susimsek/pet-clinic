package com.web;

import com.model.Owner;
import com.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PetClinicDeleteOwnerController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(value = "/owners/delete/{id}",method = RequestMethod.GET)
    public String loadOwner(@PathVariable Long id, ModelMap modelMap){
        Owner owner=petClinicService.findOwner(id);
        modelMap.put("owner",owner);
        return "deleteOwner";
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/owners/delete/{id}")
    public String handleFormSubmit(@PathVariable long id, RedirectAttributes redirectAttributes){
        petClinicService.deleteOwner(id);
        redirectAttributes.addFlashAttribute("message","Owner deleted with id :"+id);
        return "redirect:/owners";
    }

}
