package controllers.Nutritionist.Property;

import controllers.AbstractController;
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
@RequestMapping("/nutritionist/property")
public class NutritionistPropertyController extends AbstractController {

    @Autowired
    private NutritionistService nutritionistService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private IngredientService ingredientService;


    @RequestMapping(value = "/list")
    public ModelAndView index() {
        Collection<Property> propertys = propertyService.findAll();
        ModelAndView result = new ModelAndView("nutritionist/property/list");
        result.addObject("propertys", propertys);
        result.addObject("requestURI", "nutritionist/property/list.do");

        return result;
    }

    @RequestMapping(value = "/new")
    public ModelAndView newProperty() {
        return createNewView(null, null);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView newProperty(@Valid @ModelAttribute Property property, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            return createNewView(property, "wrong");
        }
        try {
            propertyService.save(property);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            System.out.print(throwable.getMessage());
            return createNewView(property, "wrong");
        }
    }

    @RequestMapping(value = "/edit")
    public ModelAndView editProperty(@RequestParam(required = true) Property propertyId) {
        Assert.notNull(propertyId);
        return createEditView(propertyId, null);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editProperty(@Valid @ModelAttribute Property property, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            return createNewView(property, "wrong");
        }
        try {
            propertyService.save(property);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            System.out.print(throwable.getMessage());
            return createNewView(property, "wrong");
        }
    }


    protected ModelAndView createNewView(Property property, String message) {
        Collection<Property> properties = propertyService.findAll();
        ModelAndView result = new ModelAndView("nutritionist/property/edit");
        if (property == null) {
            property = new Property();
        }
        result.addObject("property", property);
        result.addObject("actionURI", "/nutritionist/property/new.do");
        result.addObject("editOrCreate", "create");
        result.addObject("properties", properties);
        result.addObject("message", message);

        return result;
    }

    protected ModelAndView createEditView(Property property, String message) {
        Collection<Property> properties = propertyService.findAll();
        ModelAndView result = new ModelAndView("nutritionist/property/edit");
        if (property == null) {
            property = new Property();
        }
        result.addObject("property", property);
        result.addObject("actionURI", "/nutritionist/property/edit.do");
        result.addObject("editOrCreate", "edit");
        result.addObject("properties", properties);
        result.addObject("message", message);

        return result;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteProperty(@RequestParam Property propertyId) {
        try {
            propertyService.delete(propertyId);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            System.out.print(throwable.getMessage());
            ModelAndView result = new ModelAndView("redirect:list.do");
            return result;
        }


    }


}
