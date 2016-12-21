package controllers.User.Recipe;

import controllers.AbstractController;
import domain.*;
import org.apache.commons.collections.list.LazyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.*;

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
    private CategoryService categoryService;

    @Autowired
    private IngredientService ingredientService;


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
    @RequestMapping(value = "/new")
    public ModelAndView newRecipe(@RequestParam(required = false, defaultValue = "1") Integer nIngredients,
                                  @RequestParam(required = false, defaultValue = "1") Integer nSteps) {
        ModelAndView result;
        Collection<Ingredient> ingredients;
        Collection<Category> categories = categoryService.findAll();
        Recipe recipe = new Recipe();
        ingredients = ingredientService.findAll();
        result = new ModelAndView("user/recipe/new");
        recipe.setQuantities(new AutoPopulatingList<Quantity>(Quantity.class));
        recipe.setSteps(new AutoPopulatingList<Step>(Step.class));
        result.addObject("ingredients", ingredients);
        result.addObject("categories",categories);
        result.addObject("recipe", recipe);
        result.addObject("nIngredients",nIngredients);
        result.addObject("nSteps",nSteps);
        return result;
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST,params = "send")
    public ModelAndView newRecipePost( @ModelAttribute("recipe") @Valid Recipe recipe, BindingResult binding) {

        ModelAndView result;
        System.out.println(recipe);
        if (binding.hasErrors()){
            return creatNewModelAndView(recipe,"wrong");
        }
        try{
            recipeService.newRecipe(recipe);
            return new ModelAndView("redirect:/user/recipe/list.do");
        }catch (Throwable oops){
            return creatNewModelAndView(recipe,"wrong");
        }


    }

    protected ModelAndView creatNewModelAndView(Recipe recipe,String message){
        ModelAndView result;

        result = new ModelAndView("user/recipe/new");
        result.addObject("recipe", recipe);
        result.addObject("message",message);
        result.addObject("nIngredients",1);
        result.addObject("nSteps",1);

        return result;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteRecipe(@RequestParam(required = true)Recipe recipeId) {
        ModelAndView result;
        Assert.notNull(recipeId);
        try{
            recipeService.delete(recipeId);
        }catch (Throwable throwable){
            System.out.print(throwable.getLocalizedMessage());
        }
        return new ModelAndView("redirect:/user/recipe/list.do");
    }

}
