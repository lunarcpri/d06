package services;

import domain.Actor;
import domain.Recipe;
import domain.User;
import domain.UserOrNutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.UserOrNutritionistRepository;
import security.UserAccountService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserOrNutritionistService {

    @Autowired
    private UserOrNutritionistRepository userOrNutritionistRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ActorService actorService;



    public UserOrNutritionistService(){

        super();

    }

    //Simple CRUD Methods


    public Collection<UserOrNutritionist> findAll() {
        Collection<UserOrNutritionist> result;

        result = userOrNutritionistRepository.findAll();
        Assert.notNull(result);

        return result;
    }

    public UserOrNutritionist findOne(int userOrNutritionistId) {
        Assert.isTrue(userOrNutritionistId != 0);

        UserOrNutritionist result;

        result = userOrNutritionistRepository.findOne(userOrNutritionistId);
        Assert.notNull(result);

        return result;
    }

    public void save(UserOrNutritionist userOrNutritionist) {
        Assert.notNull(userOrNutritionist);

        userOrNutritionistRepository.save(userOrNutritionist);
    }

    public void delete(UserOrNutritionist userOrNutritionist) {
        Assert.notNull(userOrNutritionist);
        Assert.isTrue(userOrNutritionist.getId() != 0);
        Assert.isTrue(userOrNutritionistRepository.exists(userOrNutritionist.getId()));

        userOrNutritionistRepository.delete(userOrNutritionist);
    }

    public Boolean isFollowing(UserOrNutritionist follower, UserOrNutritionist following){
        Assert.notNull(following);
        return  follower.getFollowing().contains(following);
    }


    public void follow(UserOrNutritionist follower, UserOrNutritionist followed){
        Collection<UserOrNutritionist> followers;
        Collection<UserOrNutritionist> following;
        Assert.notNull(follower);
        Assert.notNull(followed);
        Assert.isTrue(follower.getId()!=followed.getId());

        Assert.isTrue(!isFollowing(follower,followed));
        followers = followed.getFollower();
        following = follower.getFollowing();
        followers.add(follower);
        following.add(followed);

        followed.setFollower(followers);
        follower.setFollowing(following);
        save(followed);
        save(follower);

    }


    public void unfollow(UserOrNutritionist unfollower, UserOrNutritionist unfollowed){

        Collection<UserOrNutritionist> unfollowers;
        Collection<UserOrNutritionist> unfollowing;
        Assert.notNull(unfollower);
        Assert.notNull(unfollowed);
        Assert.isTrue(unfollower.getId()!=unfollowed.getId());
        Assert.isTrue(isFollowing(unfollower,unfollowed));

        unfollowers = unfollowed.getFollower();
        unfollowing = unfollower.getFollowing();
        unfollowers.remove(unfollower);
        unfollowing.remove(unfollowed);

        unfollowed.setFollower(unfollowers);
        unfollower.setFollowing(unfollowing);

        save(unfollowed);
        save(unfollower);

    }
    public Collection<Recipe> latestFollowingUserRecipes(){
       Collection<Recipe> result;
        User u = userService.findByPrincipal();
        result = userOrNutritionistRepository.streamRecipesFollowingUsers(u.getId());
        Assert.notNull(result);

        return result;
    }

    public UserOrNutritionist findUserOrNutritionistByActor(Actor actor){
        UserOrNutritionist result;

        result = findOne(actor.getId());
        Assert.notNull(result);

        return result;
    }

    public Collection<Recipe> findAllRecipesByFollowingActors(){
        UserOrNutritionist userOrNutritionist = (UserOrNutritionist) actorService.findActorByPrincipal();
        Collection<Recipe> list = userOrNutritionistRepository.streamRecipesFollowingUsers(userOrNutritionist.getId());
        Assert.notNull(list);
        return list;
    }
}
