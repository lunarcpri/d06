package services;

import domain.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.StepRepository;

@Service
@Transactional
public class StepService {

    @Autowired
    private StepRepository stepRepository;


    public StepService(){
        super();
    }

    public Step save(Step s){
        Assert.notNull(s);

        return stepRepository.save(s);
    }
}
