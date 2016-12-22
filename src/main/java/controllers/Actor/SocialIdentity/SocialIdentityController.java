package controllers.Actor.SocialIdentity;

import controllers.AbstractController;
import domain.Actor;
import domain.SocialIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.SocialIdentityService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/actor/socialIdentity")
public class SocialIdentityController extends AbstractController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private SocialIdentityService socialIdentityService;

    @RequestMapping(value = "/edit")
    public ModelAndView index(@RequestParam(required = true) SocialIdentity socialIdentityId) {
        ModelAndView result;
        Assert.notNull(socialIdentityId);
        Actor principal = actorService.findActorByPrincipal();
        Assert.isTrue(principal.getSocialIdentities().contains(socialIdentityId));

        return createEditModelAndView(socialIdentityId, null);
    }

    @RequestMapping(value = "/new")
    public ModelAndView newSI() {
        ModelAndView result;

        return creatNewModelAndView(new SocialIdentity(), null);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView editPost(@Valid @ModelAttribute("socialIdentity") SocialIdentity socialIdentity,
                                 BindingResult binding) {
        ModelAndView result;
        Assert.notNull(socialIdentity);
        Actor principal = actorService.findActorByPrincipal();
        if (binding.hasErrors()) {
            return createEditModelAndView(socialIdentity, "wrong");
        }
        try {
            socialIdentityService.save(socialIdentity);
            return new ModelAndView("redirect:/actor/socialIdentity/list.do");
        } catch (Throwable oops) {
            System.out.print(oops.getLocalizedMessage());
            return createEditModelAndView(socialIdentity, "wrong");
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, params = "save")
    public ModelAndView createPost(@Valid @ModelAttribute("socialIdentity") SocialIdentity socialIdentity,
                                   BindingResult binding) {
        ModelAndView result;
        Assert.notNull(socialIdentity);
        Actor principal = actorService.findActorByPrincipal();

        socialIdentity.setActor(principal);
        result = new ModelAndView("socialIdentity/new");
        if (binding.hasErrors()) {
            return createEditModelAndView(socialIdentity, "wrong");
        }
        try {
            socialIdentityService.save(socialIdentity);
            return new ModelAndView("redirect:/actor/socialIdentity/list.do");
        } catch (Throwable oops) {
            return createEditModelAndView(socialIdentity, "wrong");
        }
    }

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<SocialIdentity> socialIdentities = actorService.findActorByPrincipal().getSocialIdentities();
        result = new ModelAndView("socialIdentity/list");
        result.addObject("socialIdentities", socialIdentities);
        return result;
    }


    protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity, String message) {
        ModelAndView result;
        Assert.notNull(socialIdentity);

        result = new ModelAndView("socialIdentity/edit");
        result.addObject("socialIdentity", socialIdentity);
        result.addObject("message", message);

        return result;
    }

    protected ModelAndView creatNewModelAndView(SocialIdentity socialIdentity, String message) {
        ModelAndView result;
        Assert.notNull(socialIdentity);

        result = new ModelAndView("socialIdentity/new");
        result.addObject("socialIdentity", socialIdentity);
        result.addObject("message", message);

        return result;
    }


    @RequestMapping(value = "/delete")
    public ModelAndView editPost(@RequestParam(required = true) SocialIdentity socialIdentityId) {
        ModelAndView result;
        Assert.notNull(socialIdentityId);
        Actor principal = actorService.findActorByPrincipal();
        Assert.isTrue(principal == socialIdentityId.getActor());
        try {
            socialIdentityService.delete(socialIdentityId);
            return new ModelAndView("redirect:/actor/socialIdentity/list.do");
        } catch (Throwable oops) {
            return new ModelAndView("redirect:/actor/socialIdentity/list.do");
        }
    }

}
