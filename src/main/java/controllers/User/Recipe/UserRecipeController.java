package controllers.User.Recipe;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

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

    @Autowired
    private ContestService contestService;


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
    public ModelAndView newRecipe(@RequestParam(required = false, defaultValue = "0") Integer nIngredients,
                                  @RequestParam(required = false, defaultValue = "0") Integer nSteps) {
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

        Collection<Category> categories = categoryService.findAll();
        result = new ModelAndView("user/recipe/new");
        result.addObject("recipe", recipe);
        result.addObject("message",message);
        result.addObject("nIngredients",1);
        result.addObject("categories",categories);
        result.addObject("nSteps",1);

        return result;
    }

    protected ModelAndView creatEditModelAndView(Recipe recipe,String message, Integer nIngredients, Integer nSteps){
        ModelAndView result;

        Collection<Ingredient> ingredients;
        Collection<Category> categories = categoryService.findAll();
        ingredients = ingredientService.findAll();
        result = new ModelAndView("user/recipe/edit");
        List<Quantity> quantityList = new AutoPopulatingList<Quantity>(Quantity.class);
        quantityList.addAll(recipe.getQuantities());
        List<Step> stepList = new AutoPopulatingList<Step>(Step.class);
        stepList.addAll(recipe.getSteps());
        recipe.setQuantities(quantityList);
        recipe.setSteps(stepList);

        if (nIngredients== null){
            nIngredients = quantityList.size();
        }
        if (nSteps == null){
            nSteps = stepList.size();
        }
        result.addObject("ingredients", ingredients);
        result.addObject("categories",categories);
        result.addObject("recipe", recipe);
        result.addObject("nIngredients",nIngredients);
        result.addObject("nSteps",nSteps);
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

    @RequestMapping(value = "/edit")
    public ModelAndView editRecipe(@RequestParam Recipe recipeId,
                                   @RequestParam(required = false) Integer nIngredients,
                                  @RequestParam(required = false) Integer nSteps) {
        return creatEditModelAndView(recipeId,null,nIngredients,nSteps);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,params = "send")
    public ModelAndView editRecipePost( @ModelAttribute("recipe") @Valid Recipe recipe, BindingResult binding) {

        ModelAndView result;
        if (binding.hasErrors()){
            return creatEditModelAndView(recipe,"wrong",recipe.getQuantities().size(),recipe.getSteps().size());
        }
        try{
            recipeService.editRecipe(recipe);
            return new ModelAndView("redirect:/user/recipe/list.do");
        }catch (Throwable oops){
            System.out.print(oops.getLocalizedMessage());
            return creatEditModelAndView(recipe,"wrong",recipe.getQuantities().size(),recipe.getSteps().size());
        }
    }

    @RequestMapping(value = "/qualifyRecipe")
    public ModelAndView qualifyRecipe(@RequestParam Recipe recipeId) {
        ModelAndView result = new ModelAndView("user/recipe/qualify");
        result.addObject("recipe",recipeId);
        if(!canQualify(recipeId.getLikes())){
            result.addObject("canQualify",false);
        }else{
            result.addObject("canQualify",true);
            Collection<Contest> contests = contestService.findOpenContests();
            result.addObject("contests",contests);
        }

       return result;
    }

    @RequestMapping(value = "/qualify")
    public ModelAndView qualifyRecipePost(@RequestParam Contest contestId,@RequestParam Recipe recipeId) {
        ModelAndView result;
        if(!canQualify(recipeId.getLikes())){
            result = new ModelAndView("redirect:list.do");
        }else{
           try{
               contestService.qualifyRecipe(recipeId,contestId);
               result = new ModelAndView("redirect:list.do");
           }catch (Throwable throwable){
               System.out.print(throwable.getMessage());
               result = new ModelAndView("redirect:list.do");
           }
        }

        return result;
    }

    protected boolean canQualify(Collection<Likes> likes){
        int dislikes = 0;
        int likees = 0;
        for(Likes l: likes){
            if (l.getIsLike()){
                likees = likees+1;
            }else{
                dislikes = dislikes+1;
            }
        }
        return dislikes == 0 && likees >= 5;
    }


}
