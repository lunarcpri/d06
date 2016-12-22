package services;

import domain.Comment;
import domain.UserOrNutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CommentRepository;
import security.UserAccountService;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserOrNutritionistService userOrNutritionistService;

    @Autowired
    private ActorService actorService;


    public CommentService(){
        super();
    }


    public Comment create(){
        return new Comment();
    }
    public Comment findOne(int id){
        Comment result;

        result = commentRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    public Comment save(Comment comment){
        Assert.notNull(comment);
        UserOrNutritionist principal = (UserOrNutritionist) actorService.findActorByPrincipal();
        Assert.isTrue(comment.getAuthor().getId()==principal.getId());
        comment.setCreated_at(new Date());
        return commentRepository.save(comment);
    }

    public Collection<Comment> findAll(){
        Collection<Comment> result;

        result = commentRepository.findAll();
        Assert.notNull(result);

        return result;
    }


}
