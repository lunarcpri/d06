package controllers.Nutritionist.Curriculum;

import controllers.AbstractController;
import domain.Curriculum;
import domain.Nutritionist;
import domain.Property;
import domain.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CurriculumService;
import services.NutritionistService;
import services.PropertyService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/nutritionist/curriculum")
public class NutritionistCurriculumController extends AbstractController {

    @Autowired
    private NutritionistService nutritionistService;

    @Autowired
    private CurriculumService curriculumService;

    @Autowired
    private PropertyService propertyService;


    @RequestMapping(value = "/list")
    public ModelAndView index() {
        Nutritionist principal = nutritionistService.findByPrincipal();
        Curriculum curriculum = principal.getCurriculum();
        ModelAndView result = new ModelAndView("nutritionist/curriculum/list");
        result.addObject("curriculum", curriculum);

        return result;
    }

    @RequestMapping(value = "/new")
    public ModelAndView newCurriculum(@RequestParam(required = false, defaultValue = "0") Integer nReferences) {
        return createNewView(null, null, nReferences);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView newCurriculum(@Valid @ModelAttribute Curriculum curriculum, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            return createNewView(curriculum, "wrong", null);
        }
        try {
            curriculumService.save(curriculum);
            return new ModelAndView(
                    "redirect:list.do");
        } catch (Throwable throwable) {
            System.out.println(curriculum.toString());
            System.out.print(throwable.getMessage());
            return createNewView(curriculum, "wrong", null);
        }
    }

    @RequestMapping(value = "/edit")
    public ModelAndView editCurriculum(@RequestParam(required = false) Integer nReferences) {
        Nutritionist principal = nutritionistService.findByPrincipal();
        Curriculum curriculum = principal.getCurriculum();
        Assert.notNull(curriculum);
        return createEditView(curriculum, null, nReferences);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editCurriculum(@Valid @ModelAttribute Curriculum curriculum, BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            return createNewView(curriculum, "wrong", null);
        }
        try {
            curriculumService.save(curriculum);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            return createNewView(curriculum, "wrong", null);
        }
    }


    protected ModelAndView createNewView(Curriculum curriculum, String message, Integer nReferences) {
        Collection<Property> properties = propertyService.findAll();
        ModelAndView result = new ModelAndView("nutritionist/curriculum/edit");
        List<Reference> references = new AutoPopulatingList<Reference>(Reference.class);
        if (curriculum == null) {
            curriculum = new Curriculum();

        } else if (curriculum.getReferences() != null) {
            references.addAll(curriculum.getReferences());
        }
        curriculum.setReferences(references);
        result.addObject("curriculum", curriculum);
        result.addObject("actionURI", "/nutritionist/curriculum/new.do");
        result.addObject("editOrCreate", "create");
        result.addObject("nReferences", nReferences);
        result.addObject("message", message);

        return result;
    }

    protected ModelAndView createEditView(Curriculum curriculum, String message, Integer nReferences) {
        Collection<Property> properties = propertyService.findAll();
        ModelAndView result = new ModelAndView("nutritionist/curriculum/edit");
        if (nReferences == null) {
            nReferences = curriculum.getReferences().size();
        }
        List<Reference> references = new AutoPopulatingList<Reference>(Reference.class);
        references.addAll(curriculum.getReferences());
        curriculum.setReferences(references);
        result.addObject("curriculum", curriculum);
        result.addObject("actionURI", "/nutritionist/curriculum/edit.do");
        result.addObject("editOrCreate", "edit");
        result.addObject("nReferences", nReferences);
        result.addObject("message", message);

        return result;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteCurriculum() {
        try {
            curriculumService.deleteByPrincipal();
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            ModelAndView result = new ModelAndView("redirect:list.do");
            result.addObject("message", "wrong");

            return result;
        }


    }


}
