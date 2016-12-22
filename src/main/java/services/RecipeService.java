package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.RecipeRepository;
import security.UserAccountService;

import java.util.*;

@Service
@Transactional
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserOrNutritionistService userOrNutritionistService;

    @Autowired
    private ContestService contestService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private LikesService likesService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StepService stepService;

    @Autowired
    QuantityService quantityService;

    public RecipeService() {

        super();
    }


    public Collection<Recipe> findAll() {

        return recipeRepository.findAll();
    }

    public Recipe findOne(int id) {
        Recipe result;

        result = recipeRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    public Recipe create() {
        Recipe result;

        result = new Recipe();

        return result;
    }

    public Recipe save(Recipe recipe) {
        Assert.notNull(recipe);

        return recipeRepository.save(recipe);
    }

    public Recipe newRecipe(Recipe recipe) {

        userAccountService.assertRole("USER");
        Date createdAt = new Date();
        recipe.setUpdated_at(createdAt);
        recipe.setCreated_at(createdAt);
        recipe.setTicker(generateTicker());
        recipe.setAuthor(userService.findByPrincipal());

        Recipe recipe2 = save(recipe);
        for (Step s : recipe.getSteps()) {
            s.setRecipe(recipe2);
            stepService.save(s);
        }
        for (Quantity q : recipe.getQuantities()) {
            q.setRecipe(recipe2);
            quantityService.save(q);
        }
        return recipe2;
    }


    public Recipe editRecipe(Recipe recipe) {

        userAccountService.assertRole("USER");
        Date createdAt = new Date();
        recipe.setUpdated_at(createdAt);
        recipe.setAuthor(userService.findByPrincipal());

        Recipe recipe2 = save(recipe);
        for (Step s : recipe.getSteps()) {
            s.setRecipe(recipe2);
            stepService.save(s);
        }
        for (Quantity q : recipe.getQuantities()) {
            q.setRecipe(recipe2);
            quantityService.save(q);
        }
        return recipe2;
    }

    private String generateTicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String result = calendar.get(Calendar.YEAR) % 100 + "" + calendar.get(Calendar.MONTH) + "" + calendar.get(Calendar.DAY_OF_MONTH) + "-";
        Date date = new Date();
        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            result = result + String.valueOf(c);
        }
        return result;

    }


    public Collection<Recipe> findByKeyword(String key) {
        Collection<Recipe> result;

        result = recipeRepository.findByKeyword(key);
        Assert.notNull(result);

        return result;
    }


    public void delete(Recipe recipe) {
        Assert.notNull(recipe);
        userAccountService.assertRole("USER");
        Collection<Contest> contests = recipe.getQualifiedContests();
        for (Contest c : contests) {
            c.getRecipesQualified().remove(recipe);
            contestService.save(c);
        }
        contests = recipe.getWinnedContests();
        for (Contest c : contests) {
            c.getRecipesQualified().remove(recipe);
            contestService.save(c);
        }
        recipeRepository.delete(recipe);
    }


    public List<Object[]> findStdevAvgStepsPerRecipe() {
        List<Object[]> result;

        result = recipeRepository.findStdevAvgStepsPerRecipe();
        Assert.notNull(result);

        return result;
    }

    public List<Object[]> findStdevAvgIngredientsPerRecipe() {
        List<Object[]> result;

        result = recipeRepository.findStdevAvgIngredientsPerRecipe();
        Assert.notNull(result);

        return result;
    }

    public Integer getNumberOfDisLike(Recipe r) {
        Integer result;

        result = recipeRepository.getNumberOfDisLike(r.getId());

        return (result != null) ? result : 0;
    }


    public Integer getNumberOfLike(Recipe r) {
        Integer result;

        result = recipeRepository.getNumberOfLike(r.getId());

        return (result != null) ? result : 0;
    }

}
