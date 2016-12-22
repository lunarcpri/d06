package services;

import domain.User;
import domain.UserOrNutritionist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"
})
@Transactional
public class UserServiceTest extends AbstractTest {


    @Autowired
    private ActorService actorService;

    @Autowired
    private UserOrNutritionistService userOrNutritionistService;


    @Test
    public void testFindUserWithMoreRecipes() {
        User result = userService.findUserWithMoreRecipes();
        Collection<User> users = userService.findAll();
        User aux = null;
        for (User e : users) {
            if (aux == null || aux.getRecipes().size() < e.getRecipes().size()) {
                aux = e;
            }
        }
        Assert.isTrue(aux == result);
    }

    @Test
    public void testFollows() {
        super.authenticate("nutritionist1");
        UserOrNutritionist follower = (UserOrNutritionist) actorService.findActorByPrincipal();
        UserOrNutritionist followed = (UserOrNutritionist) actorService.findOne(13);
        System.out.print(userOrNutritionistService.isFollowing(follower, followed));
        userOrNutritionistService.follow(follower, followed);
        System.out.print(userOrNutritionistService.isFollowing(follower, followed));
        userOrNutritionistService.unfollow(follower, followed);
        System.out.print(userOrNutritionistService.isFollowing(follower, followed));

    }
}
