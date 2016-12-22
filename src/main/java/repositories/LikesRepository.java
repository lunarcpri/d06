package repositories;

import domain.Likes;
import domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {


    @Query("select r from UserOrNutritionist u join u.likes r where r.id=?2 and u.id=?1 and r.isLike=?3")
    Collection<Recipe> isRecipeLikedByActor(int actorid, int recipeid, boolean liked);

    @Query("select l from Likes l where l.userOrNutritionist.id=?1 and l.recipe.id = ?2")
    Likes findRecipeLikeByActor(int actorid, int recipeid);
}
