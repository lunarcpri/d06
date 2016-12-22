package services;

import domain.Likes;
import domain.Recipe;
import domain.UserOrNutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.LikesRepository;

import java.util.Collection;

@Service
@Transactional
public class LikesService {

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ActorService actorService;


    public LikesService() {
        super();
    }

    public Likes create() {
        return new Likes();
    }


    public void delete(Likes likes) {
        Assert.notNull(likes);

        likesRepository.delete(likes);
    }


    public Likes save(Likes likes) {
        Assert.notNull(likes);

        return likesRepository.save(likes);
    }

    public Likes like(Recipe recipe) {
        UserOrNutritionist u = (UserOrNutritionist) actorService.findActorByPrincipal();
        Likes likeresult;
        Likes likes = likesRepository.findRecipeLikeByActor(u.getId(), recipe.getId());
        if (likes != null) {
            if (likes.getIsLike()) {
                delete(likes);
                likeresult = null;
            } else {
                likes.setIsLike(true);
                likeresult = save(likes);

            }
        } else {
            likes = new Likes();
            likes.setIsLike(true);
            likes.setUserOrNutritionist(u);
            likes.setRecipe(recipe);
            likeresult = save(likes);
        }

        return likeresult;
    }


    public boolean doesUserLikedRecipe(Recipe recipe) {
        UserOrNutritionist u = (UserOrNutritionist) actorService.findActorByPrincipal();
        Likes like = likesRepository.findRecipeLikeByActor(u.getId(), recipe.getId());
        return (like != null) ? like.getIsLike() : false;
    }

    public boolean doesUserDisLikedRecipe(Recipe recipe) {
        UserOrNutritionist u = (UserOrNutritionist) actorService.findActorByPrincipal();
        Likes like = likesRepository.findRecipeLikeByActor(u.getId(), recipe.getId());
        return (like != null) && !like.getIsLike();
    }


    public Likes dislike(Recipe recipe) {
        UserOrNutritionist u = (UserOrNutritionist) actorService.findActorByPrincipal();
        Likes likeresult;
        Likes likes = likesRepository.findRecipeLikeByActor(u.getId(), recipe.getId());
        if (likes != null) {
            if (likes.getIsLike()) {
                likes.setIsLike(false);
                likeresult = save(likes);
            } else {
                delete(likes);
                likeresult = null;

            }
        } else {
            likes = new Likes();
            likes.setIsLike(false);
            likes.setUserOrNutritionist(u);
            likes.setRecipe(recipe);
            likeresult = save(likes);
        }

        return likeresult;
    }


}
