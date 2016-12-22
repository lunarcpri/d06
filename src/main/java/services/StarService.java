package services;

import domain.Actor;
import domain.Comment;
import domain.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.StarRepository;

@Service
@Transactional
public class StarService {

    @Autowired
    private StarRepository starRepository;

    public StarService() {
        super();
    }

    public Star save(Star star) {
        Assert.notNull(star);

        return starRepository.save(star);
    }

    public void delete(Star star) {
        Assert.notNull(star);

        starRepository.delete(star);
    }

    public Star findStarByActorAndComment(Actor actor, Comment comment) {
        Assert.notNull(actor);
        Assert.notNull(comment);

        return starRepository.findStarByActorAndComment(actor.getId(), comment.getId());
    }
}
