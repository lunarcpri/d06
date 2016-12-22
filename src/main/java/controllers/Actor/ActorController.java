package controllers.Actor;

import controllers.AbstractController;
import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;

import javax.validation.Valid;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

    @Autowired
    private ActorService actorService;


    @RequestMapping(value = "/edit")
    public ModelAndView index(@RequestParam(required = false, defaultValue = "personal") String edit) {
        ModelAndView result;
        Actor actor;
        result = new ModelAndView("actor/edit");
        actor = actorService.findActorByPrincipal();
        result.addObject("actor", actor);
        result.addObject("edit", edit);
        result.addObject("role", actor.getClass().getName());

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView edit(
            @Valid @ModelAttribute("role") Actor actor, BindingResult binding
    ) {
        Actor principal = actorService.findActorByPrincipal();
        ModelAndView result;
        result = index("personal");

        if (binding.hasErrors()) {
            System.out.print("binding error");
        } else {
            try {
                actor.setUserAccount(principal.getUserAccount());
                actor.setFolders(principal.getFolders());
                actorService.save(actor);
                System.out.println("No error");
            } catch (Throwable oops) {
                System.out.print(oops.toString());
                result.addObject("message", "wrong");
            }

        }

        return result;
    }


}
