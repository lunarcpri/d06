package repositories;

import domain.Likes;
import domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {



    @Query("select l from Likes l where l.userOrNutritionist.id=?1 and l.recipe.id = ?2")
    Likes findRecipeLikeByActor(int actorid, int recipeid);
}
