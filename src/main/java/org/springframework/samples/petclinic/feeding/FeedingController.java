package org.springframework.samples.petclinic.feeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeedingController {

    @Autowired
    public FeedingService feedingService;

    private static final String VIEWS_CREATE_OR_UPDATE_FEEDING_FORM =  "/createOrUpdateFeedingForm";

    @RequestMapping("/feedings/create")
    public String initCreationForm(ModelMap model){

        Feeding feeding = new Feeding();
        model.put("feeding", feeding);
        return VIEWS_CREATE_OR_UPDATE_FEEDING_FORM;
    }

    
}
