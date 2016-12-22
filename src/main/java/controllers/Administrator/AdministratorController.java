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
import services.ContestService;
import services.SpamTagsService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {


    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContestService contestService;

    @RequestMapping(value = "/index", method= RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView result;

        result = new ModelAndView("administrator/index");

        return result;
    }

    @RequestMapping(value = "/dashboard", method= RequestMethod.GET)
    public ModelAndView dashboard(){
        ModelAndView result;

        result = new ModelAndView("administrator/dashboard");
        for(Object[] e:userService.findMinMaxAvgRecipesPerUser()){
            result.addObject("maxrecipesperuser",e[1]);
            result.addObject("minrecipesperuser",e[0]);
            result.addObject("avgrecipesperuser",e[2]);
        }
        //
        User userMoreRecipe = userService.findUserWithMoreRecipes();
        result.addObject("usermorerecipes",userMoreRecipe);
        //
        for(Object[] e:contestService.findMinMaxAvgRecipesPerContest()){
            result.addObject("maxrecipespercontest",e[1]);
            result.addObject("minrecipespercontest",e[0]);
            result.addObject("avgrecipespercontest",e[2]);
        }
        //
        result.addObject("contestmorerecipes",contestService.findContestWithMoreRecipes());
        return result;
    }
}
