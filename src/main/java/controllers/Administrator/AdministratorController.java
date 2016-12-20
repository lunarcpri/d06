package controllers.Administrator;

import domain.SpamTags;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;
import services.SpamTagsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {


    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private SpamTagsService spamTagsService;

    @RequestMapping(value = "/spamtag/create")
    public ModelAndView createSpamTag() {
        ModelAndView result;
        SpamTags spamTags = new SpamTags();
        return createSpamTagView(spamTags);

    }

    @RequestMapping(value="/spamtag/create",method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("spamTags") @Valid SpamTags spamTags, BindingResult bindingResult){
        ModelAndView result = new ModelAndView();

        if(bindingResult.hasErrors()){
            result = createSpamTagView(spamTags);
        }
            return result;
    }

     protected ModelAndView createSpamTagView(SpamTags spamTags) {
        ModelAndView result;
        result = new ModelAndView("/administrator/createTag");
        result.addObject("createTag", spamTags);
        return result;

    }
}
