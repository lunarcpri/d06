package services;

import domain.Contest;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ContestRepository;
import security.UserAccountService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContestService {

    @Autowired
    private ContestRepository contestRepository;


    @Autowired
    UserService userService;


    @Autowired
    AdministratorService administratorService;

    @Autowired
    RecipeService recipeService;


    public ContestService() {
        super();
    }


    public Contest save(Contest contest) {
        Assert.notNull(contest);

        return contestRepository.save(contest);
    }

    public Contest findOne(int id) {
        Contest result;

        result = contestRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    public void delete(Contest contest) {
        Assert.notNull(contest);
        Assert.isTrue(contest.getRecipesQualified().isEmpty());

        contestRepository.delete(contest);


    }

    public Collection<Contest> findAll() {
        Collection<Contest> result;

        result = contestRepository.findAll();
        Assert.notNull(result);

        return result;
    }



    public Collection<Contest> findOpenContests() {
        return contestRepository.findOpenContests();
    }

    public void qualifyRecipe(Recipe r, Contest c) {
        Assert.notNull(r);
        Assert.notNull(c);
        Assert.isTrue(recipeService.getNumberOfDisLike(r) == 0 && recipeService.getNumberOfLike(r) >= 5);
        r.setRead_only(true);
        Collection<Recipe> recipeCollection = c.getRecipesQualified();
        recipeCollection.add(r);
        c.setRecipesQualified(recipeCollection);
        save(c);
    }



    public void processWinner() {
        Collection<Contest> result;


        result = contestRepository.findClosedContests();
        for (Contest e : result) {
            List<Recipe> recipes = findContestRecipesOrderByLikes(e.getId());
            if (recipes.size() > 0) {
                int winners = (recipes.size() > 3) ? 3 : recipes.size();
                List<Recipe> winnersList = recipes.subList(0, winners);
                e.setWinnerRecipes(winnersList);
                for (Recipe w : winnersList) {
                    Collection<Contest> winnedContest = w.getWinnedContests();
                    winnedContest.add(e);
                    w.setWinnedContests(winnedContest);
                    recipeService.save(w);
                }
            }
            e.setEnded(true);
            save(e);
            for (Recipe f : e.getRecipesQualified()) {
                if (findOpenContestsByRecipe(f).size() == 0) {
                    f.setRead_only(false);
                    recipeService.save(f);
                }
            }

        }
    }


    public Collection<Contest> findOpenContestsByRecipe(Recipe r) {
        Collection<Contest> result;

        result = contestRepository.findOpenContestsByRecipe(r.getId());
        Assert.notNull(result);

        return result;
    }


    public List<Recipe> findContestRecipesOrderByLikes(int id) {
        List<Recipe> result = new ArrayList<Recipe>();

        Collection<Object[]> collection = contestRepository.findContestRecipesOrderByLikes(id);
        Assert.notNull(collection);

        for (Object[] e : collection) {
            result.add((Recipe) e[0]);
        }

        return result;
    }

    public List<Object[]> findMinMaxAvgRecipesPerContest() {
        List<Object[]> result;

        result = contestRepository.findMinMaxAvgRecipesPerContest();
        Assert.notNull(result);

        return result;
    }

    public Contest findContestWithMoreRecipes() {
        List<Contest> result;

        result = contestRepository.findContestWithMoreRecipes();
        Assert.notNull(result);

        return result.get(0);
    }
}

