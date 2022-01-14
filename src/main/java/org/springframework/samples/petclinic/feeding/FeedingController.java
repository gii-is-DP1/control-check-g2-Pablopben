package org.springframework.samples.petclinic.feeding;

import java.lang.ProcessBuilder.Redirect;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeedingController {

    @Autowired
    public FeedingService feedingService;

    private static final String VIEWS_CREATE_OR_UPDATE_FEEDING_FORM =  "feedings/createOrUpdateFeedingForm";

    @RequestMapping("/feeding/create")
    public String initCreationForm(ModelMap model){

        Feeding feeding = new Feeding();
        model.addAttribute("feeding", feeding);
        model.addAttribute("feedingTypes", this.feedingService.getAllFeedingTypes());
        model.addAttribute("types", this.feedingService.getAll());
        return VIEWS_CREATE_OR_UPDATE_FEEDING_FORM;
    }

    @PostMapping("/feeding/create")
	public String processCreationForm(@Valid Feeding feeding, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("feeding", feeding);
			return VIEWS_CREATE_OR_UPDATE_FEEDING_FORM;
		}
		else {
            try{
                this.feedingService.save(feeding);
            }catch(UnfeasibleFeedingException ex){
                result.rejectValue("feedingType", "non conformant", "The selected pet cannot be assigned the specified plan");
                return VIEWS_CREATE_OR_UPDATE_FEEDING_FORM;
            }
            return "redirect:/welcome";
		}
	}

    
}
