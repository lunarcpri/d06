package repositories;

import domain.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<Star, Integer> {

    @Query("select s from Star s where s.actor.id=?1 and s.comment.id=?2")
    Star findStarByActorAndComment(Integer actorId, Integer commentId);
}
