package repositories;

import domain.Likes;
import domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {



    @Query("select r from Recipe r where r.ticker like %?1% or r.title like %?1% or r.summary like %?1%")
    Collection<Recipe> findByKeyword(String key);


    @Query("select stddev(r.steps.size),avg(r.steps.size) from Recipe r")
    List<Object[]> findStdevAvgStepsPerRecipe();

    @Query("select stddev(r.quantities.size), avg(r.quantities.size) from Recipe r")
    List<Object[]> findStdevAvgIngredientsPerRecipe();

    @Query("select count(l) from Recipe r join r.likes l where r.id=?1 and l.isLike=1")
    Integer getNumberOfLike(int id);


    @Query("select count(l) from Recipe r join r.likes l where r.id=?1 and l.isLike=0")
    Integer getNumberOfDisLike(int id);

}
