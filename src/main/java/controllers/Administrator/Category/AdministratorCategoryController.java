package controllers.Administrator.Category;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repositories.CategoryRepository;
import services.CategoryService;
import services.RecipeService;
import sun.misc.Request;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("administrator/category")
public class AdministratorCategoryController {

    @Autowired
    CategoryService categoryService;


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeService recipeService;

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
    public ModelAndView newCategory(){

    Category category = new Category();
    Collection<Category> categories = categoryService.findAll();
    Collection<Recipe> recipes = recipeService.findAll();

       ModelAndView result;
       result = new ModelAndView("administrator/category/new");
       result.addObject(category);
       result.addObject("categories", categories);
       result.addObject("recipes", recipes);
  return result;
   }

   @RequestMapping(value = "/new", method = RequestMethod.POST, params = "save")
   public ModelAndView newCategory(@Valid Category category){
        ModelAndView result;

        try{
            categoryService.save(category);
            result = new ModelAndView("redirect:list.do");
        }catch (Throwable oops){
            result = createNewModelAndView(category, "wrong");
        }
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
    public ModelAndView save(@Valid Category category, BindingResult bindingResult) {
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

    @RequestMapping(value = "/delete")
    public ModelAndView deleteCategory(@RequestParam int categoryId){
        ModelAndView result;
        Category category = categoryRepository.findCategoryById(categoryId);
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
        Collection<Category> categories = categoryService.findAll();
        Collection<Recipe> recipes = recipeService.findAll();
        Assert.notNull(category);

        result = new ModelAndView("administrator/category/new");
        result.addObject("actionURI","/administrator/category/new.do");
        result.addObject("category",category);
        result.addObject("childrens", categories);
        result.addObject("recipes", recipes);
        result.addObject("categories", categories);
        result.addObject("message",message);
        result.addObject("parent", categories);

        return  result;
    }

    protected ModelAndView createEditModelAndView(Category category, String message){
        ModelAndView result;
        Assert.notNull(category);
        Collection<Category> categories = categoryService.findAll();

        result = new ModelAndView("administrator/category/edit");
        result.addObject("category",category);
        result.addObject("message",message);
        result.addObject("categories", categories);

        return  result;
    }

}
