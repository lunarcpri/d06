package controllers.Administrator.SpamTags;

import domain.SpamTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.SpamTagsService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/administrator/spamTags")
public class SpamTagsController {


    @Autowired
    private SpamTagsService spamTagsService;

    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView result;
        Collection<SpamTags> spamTags;

        spamTags = spamTagsService.findAll();
        result = new ModelAndView("spamTags/list");
        result.addObject("spamTags", spamTags);
        result.addObject("requestURI", "spamTags/list.do");

        return result;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newSpamTag(){

        ModelAndView result;
        result = new ModelAndView("administrator/spamTags/createTag");
        result.addObject("spamTags",new SpamTags());

        return result;

    }

    @RequestMapping(value="/new", method=RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid SpamTags spamTags, BindingResult bindingResult){
        ModelAndView result;

        if(bindingResult.hasErrors()){
            result = createEditSpamTagView(spamTags, "wrong");
        }
        else{
            try{
                spamTagsService.save(spamTags);
                result = new ModelAndView("redirect:spamTags/list.do");
            }
            catch (Throwable oops){
                result = createEditSpamTagView(spamTags, "spamTags.commit.error");
            }
        }
        return result;
    }

    protected ModelAndView createEditSpamTagView(SpamTags spamTags, String message) {

        ModelAndView result;

        result = new ModelAndView("/administrator/createTag");
        result.addObject("spamTags", spamTags);
        result.addObject("message", message);
        return result;

    }
}
