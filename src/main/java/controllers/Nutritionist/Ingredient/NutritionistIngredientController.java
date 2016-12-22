package controllers.Nutritionist.Ingredient;

import controllers.AbstractController;
import domain.Ingredient;
import domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.IngredientService;
import services.NutritionistService;
import services.PropertyService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/nutritionist/ingredient")
public class NutritionistIngredientController extends AbstractController {

    @Autowired
    private NutritionistService nutritionistService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PropertyService propertyService;


    @RequestMapping(value = "/list")
    public ModelAndView index() {
        Collection<Ingredient> ingredients = ingredientService.findAll();
        ModelAndView result = new ModelAndView("nutritionist/ingredient/list");
        result.addObject("ingredients", ingredients);
        result.addObject("requestURI", "nutritionist/ingredient/list.do");

        return result;
    }

    @RequestMapping(value = "/new")
    public ModelAndView newIngredient() {
        return createNewView(null, null);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView newIngredient(@Valid @ModelAttribute Ingredient ingredient, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            return createNewView(ingredient, "wrong");
        }
        try {
            ingredientService.save(ingredient);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            System.out.print(throwable.getMessage());
            return createNewView(ingredient, "wrong");
        }
    }

    @RequestMapping(value = "/edit")
    public ModelAndView editIngredient(@RequestParam(required = true) Ingredient ingredientId) {
        Assert.notNull(ingredientId);
        return createEditView(ingredientId, null);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editIngredient(@Valid @ModelAttribute Ingredient ingredient, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            return createNewView(ingredient, "wrong");
        }
        try {
            ingredientService.save(ingredient);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            System.out.print(throwable.getMessage());
            return createNewView(ingredient, "wrong");
        }
    }


    protected ModelAndView createNewView(Ingredient ingredient, String message) {
        Collection<Property> properties = propertyService.findAll();
        ModelAndView result = new ModelAndView("nutritionist/ingredient/edit");
        if (ingredient == null) {
            ingredient = new Ingredient();
        }
        result.addObject("ingredient", ingredient);
        result.addObject("actionURI", "/nutritionist/ingredient/new.do");
        result.addObject("editOrCreate", "create");
        result.addObject("properties", properties);
        result.addObject("message", message);

        return result;
    }

    protected ModelAndView createEditView(Ingredient ingredient, String message) {
        Collection<Property> properties = propertyService.findAll();
        ModelAndView result = new ModelAndView("nutritionist/ingredient/edit");
        if (ingredient == null) {
            ingredient = new Ingredient();
        }
        result.addObject("ingredient", ingredient);
        result.addObject("actionURI", "/nutritionist/ingredient/edit.do");
        result.addObject("editOrCreate", "edit");
        result.addObject("properties", properties);
        result.addObject("message", message);

        return result;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteIngredient(@RequestParam Ingredient ingredientId) {
        try {
            ingredientService.delete(ingredientId);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            ModelAndView result = new ModelAndView("redirect:list.do");
            result.addObject("message", "wrong");

            return result;
        }


    }


}
