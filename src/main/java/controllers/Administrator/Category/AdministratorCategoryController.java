package controllers.Administrator.Category;

import com.sun.javafx.sg.prism.NGShape;
import domain.Actor;
import domain.Category;
import domain.Folder;
import domain.SpamTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CategoryService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("administrator/category")
public class AdministratorCategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Collection<Category> categoriesCollection;
        result = new ModelAndView("administrator/category/list");
        categoriesCollection = categoryService.findAll();

        result.addObject("categories", categoriesCollection);
        result.addObject("requestURI","administrator/category/list.do");
        return result;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newSpamTag(){

        ModelAndView result;
        result = new ModelAndView("administrator/spamTags/createTag");
        result.addObject("spamTags",new SpamTags());

        return result;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int categoryId) {

        ModelAndView result;
        Category category;

        category = categoryService.findOne(categoryId);
        Assert.notNull(category);
        result = createEditModelAndView(category, null);
        result.addObject(category);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@RequestParam Category category, BindingResult bindingResult) {
        ModelAndView result;

        if(bindingResult.hasErrors()){
            result = createEditModelAndView(category, "wrong");
        }else{
            try{
                categoryService.save(category);
                result = new ModelAndView("redirect:list.do");
            }catch (Throwable oops){
                result = createEditModelAndView(category, "administrator.categories.commit.error");
            }
        }
            return result;

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(Category category, BindingResult bindingResult){
        ModelAndView result;
        try{
            categoryService.delete(category);
            result = new ModelAndView("redirect:list.do");

        }catch (Throwable oops){
            result = createEditModelAndView(category, "administrator.categories.delete.error");
        }
        return result;
    }



    protected ModelAndView createNewModelAndView(Category category, String message){
        ModelAndView result;
        Assert.notNull(category);

        result = new ModelAndView("administrator/category/new");
        result.addObject("category",category);
        result.addObject("message",message);

        return  result;
    }

    protected ModelAndView createEditModelAndView(Category category, String message){
        ModelAndView result;
        Assert.notNull(category);

        result = new ModelAndView("administrator/category/edit");
        result.addObject("category",category);
        result.addObject("message",message);

        return  result;
    }

}
