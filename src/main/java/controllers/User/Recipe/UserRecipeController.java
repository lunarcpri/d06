package controllers.User.Recipe;

import controllers.AbstractController;
import domain.Actor;
import domain.Recipe;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.RecipeService;
import services.UserService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/user/recipe")
public class UserRecipeController extends AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Recipe> recipeCollection;
        User principal = userService.findByPrincipal();
        recipeCollection =  principal.getRecipes();
        result = new ModelAndView("user/recipe/list");


        result.addObject("recipes", recipeCollection);
        result.addObject("requestURI","user/recipe/list.do");
        return result;
    }


}
