package controllers.Administrator.Contest;

import domain.Category;
import domain.Contest;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repositories.ContestRepository;
import services.ContestService;
import services.RecipeService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("administrator/contest")
public class AdministratorContestController {

    @Autowired
    private ContestService contestService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ContestRepository contestRepository;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Contest> contestCollection;
        result = new ModelAndView("administrator/contest/list");
        contestCollection = contestService.findAll();

        result.addObject("contests", contestCollection);
        result.addObject("requestURI","administrator/contest/list.do");
        return result;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newContest(){

        Contest contest = new Contest();

        ModelAndView result;
        result = new ModelAndView("administrator/contest/new");
        result.addObject(contest);
        return result;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, params = "save")
    public ModelAndView newContest(@Valid Contest contest, BindingResult binding){
        ModelAndView result;
            if (binding.hasErrors()){
                System.out.println(binding.getAllErrors().get(0));
                result = createNewModelAndView(contest, "wrong");
            }
        try{
            contestService.save(contest);
            result = new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            System.out.print(oops.getMessage());
            result = createNewModelAndView(contest, "wrong");
        }
        return result;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int contestId) {

        ModelAndView result;
        Contest contest;

        contest = contestService.findOne(contestId);
        Assert.notNull(contest);
        result = createEditModelAndView(contest, null);
        result.addObject(contest);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid Contest contest, BindingResult bindingResult) {
        ModelAndView result;

        if(bindingResult.hasErrors()){
            result = createEditModelAndView(contest, "wrong");
        }else{
            try{
                contestService.save(contest);
                result = new ModelAndView("redirect:list.do");
            }catch (Throwable oops){
                result = createEditModelAndView(contest, "wrong");
            }
        }
        return result;

    }


    @RequestMapping(value = "/delete")
    public ModelAndView deleteContest(@RequestParam int contestId){
        ModelAndView result;
        Contest contest = contestRepository.findContestById(contestId);
        try{
            contestService.delete(contest);
            result = new ModelAndView("redirect:list.do");

        }catch (Throwable oops){
            result = createEditModelAndView(contest, "wrong");
        }
        return result;
    }

    protected ModelAndView createNewModelAndView(Contest contest, String message){
        ModelAndView result;
        Assert.notNull(contest);

        result = new ModelAndView("administrator/contest/new");
        result.addObject("actionURI","/administrator/contest/new.do");
        result.addObject("contest",contest);
        result.addObject("message",message);

        return  result;
    }

    protected ModelAndView createEditModelAndView(Contest contest, String message){
        ModelAndView result;
        Assert.notNull(contest);

        result = new ModelAndView("administrator/contest/edit");
        result.addObject("contest", contest);
        result.addObject("message",message);

        return  result;
    }
}
