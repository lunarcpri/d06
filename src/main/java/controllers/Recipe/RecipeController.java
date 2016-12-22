package controllers.Recipe;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.LoginService;
import services.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/recipe")
public class RecipeController extends AbstractController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private LikesService likesService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private StarService starService;

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(required=false) String query) {
        ModelAndView result;
        Collection<Recipe> recipeCollection;
        result = new ModelAndView("recipe/list");
        recipeCollection = recipeService.findAll();

        if (query!= null){
            recipeCollection = recipeService.findByKeyword(query);
        }
        result.addObject("recipes", recipeCollection);
        result.addObject("requestURI","recipe/list.do");
        return result;
    }


    @RequestMapping(value = "/{recipe}")
    public ModelAndView index(@PathVariable Recipe recipe) {
        return createIndexView(recipe,null);
    }


    protected ModelAndView createIndexView(Recipe recipe,String message){
        ModelAndView result;
        Assert.notNull(recipe);
        result = new ModelAndView("recipe/index");
        result.addObject("canlike",false);
        if (LoginService.isAuthorized()){
            UserOrNutritionist principal = (UserOrNutritionist) actorService.findActorByPrincipal();
            Comment comment = new Comment();
            comment.setAuthor(principal);
            comment.setRecipe(recipe);
            result.addObject("comment",comment);
            if (principal.getId()!=recipe.getAuthor().getId()) {
                result.addObject("canlike",true);
                result.addObject("liked",likesService.doesUserLikedRecipe(recipe));
                result.addObject("disliked",likesService.doesUserDisLikedRecipe(recipe));
            }
        }
        result.addObject("recipe", recipe);
        result.addObject("likes",recipeService.getNumberOfLike(recipe));
        result.addObject("dislikes",recipeService.getNumberOfDisLike(recipe));
        result.addObject("likeURI","recipe/like.do?recipeId="+recipe.getId());
        result.addObject("dislikeURI","recipe/dislike.do?recipeId="+recipe.getId());
        result.addObject("requestURI","recipe/index.do");
        result.addObject("title",recipe.getTitle());
        return result;
    }
    @RequestMapping(value = "/like")
    public ModelAndView likeRecipe(@RequestParam Recipe recipeId) {
        Recipe recipe = recipeId;
        ModelAndView result;
        Assert.notNull(recipe);
        if (LoginService.isAuthorized()){
            UserOrNutritionist principal = (UserOrNutritionist) actorService.findActorByPrincipal();
            if (principal.getId()!=recipe.getAuthor().getId()) {
                likesService.like(recipe);
            }
        }
        return new ModelAndView("redirect:"+recipe.getId()+".do");
    }

    @RequestMapping(value = "/dislike")
    public ModelAndView dislikeRecipe(@RequestParam Recipe recipeId) {
        Recipe recipe = recipeId;
        ModelAndView result;
        Assert.notNull(recipe);
        UserOrNutritionist principal = (UserOrNutritionist) actorService.findActorByPrincipal();
        if (principal.getId()!=recipe.getAuthor().getId()) {
            likesService.dislike(recipe);
        }
        return new ModelAndView("redirect:"+recipe.getId()+".do");
    }

    @RequestMapping(value = "/{recipe}",method = RequestMethod.POST,params = "tocomment")
    public ModelAndView comment(@ModelAttribute("comment") @Valid Comment comment, BindingResult binding,
                                @PathVariable Recipe recipe){
        ModelAndView result;

        if (binding.hasErrors()){
            result = createIndexView(recipe,"wrong");
            for(ObjectError o: binding.getAllErrors()){

            }
            return result;
        }
        try{
            commentService.save(comment);
            return new ModelAndView("redirect:"+recipe.getId()+".do");
        }catch (Throwable throwable){
            System.out.print(throwable.getMessage());
            result = createIndexView(recipe,"wrong");
            return result;
        }
    }

    @RequestMapping(value = "/rate")
    public ModelAndView rateRecipe(@RequestParam Comment commentId,
                                   @RequestParam Integer stars) {
        Comment comment = commentId;
        Recipe recipe = comment.getRecipe();
        Actor principal = actorService.findActorByPrincipal();
        ModelAndView result;
        Assert.notNull(recipe);
        Assert.isTrue(stars>=0 && stars<=5);
        Star curentStar = starService.findStarByActorAndComment(principal,comment);
        if (curentStar == null){
            curentStar = new Star();
            curentStar.setActor(principal);
            curentStar.setComment(comment);
        }
        curentStar.setStars(stars);
        starService.save(curentStar);
        return new ModelAndView("redirect:"+recipe.getId()+".do");
    }
}
