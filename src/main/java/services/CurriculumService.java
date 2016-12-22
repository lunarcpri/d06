package services;

import domain.Actor;
import domain.Curriculum;
import domain.Nutritionist;
import domain.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CurriculumRepository;
import security.UserAccountService;

@Service
@Transactional
public class CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private NutritionistService nutritionistService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ReferenceService referenceService;

    public CurriculumService(){
        super();
    }


    private Curriculum create(){
        return new Curriculum();
    }


    public Curriculum findOne(int id){
        Curriculum result;

        result = curriculumRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    public Curriculum findByActor(int id){
        Curriculum result;

        result = curriculumRepository.findCurriculumByActor(id);
        Assert.notNull(result);

        return result;
    }

    public void deleteCurriculum(int id){
        Actor a = nutritionistService.findByPrincipal();
        Curriculum curriculum = findOne(id);

        if (curriculum.getNutritionist() == a){
            delete(curriculum);
        }
    }

    public void deleteByPrincipal(){
        Nutritionist a = nutritionistService.findByPrincipal();
        Curriculum curriculum = a.getCurriculum();
        a.setCurriculum(null);
        nutritionistService.save(a);
    }

    public void delete(Curriculum curriculum){
        Assert.notNull(curriculum);
        Nutritionist nutritionist = nutritionistService.findByPrincipal();
        curriculum.setNutritionist(nutritionist);

        curriculumRepository.delete(curriculum);
    }

    public void save(Curriculum curriculum){
        Assert.notNull(curriculum);
        Curriculum curriculum1 = curriculum;
        Nutritionist nutritionist = nutritionistService.findByPrincipal();
        curriculum = curriculumRepository.save(curriculum);
        nutritionist.setCurriculum(curriculum);
        nutritionistService.save(nutritionist);
        if (curriculum1.getReferences()!= null){
            for (Reference r: curriculum1.getReferences()){
                if (r == null) continue;
                r.setCurriculum(curriculum);
                referenceService.save(r);
            }
        }
    }


    public void newCurriculum(Curriculum curriculum){
        userAccountService.assertRole("NUTRITIONIST");
        Nutritionist nutritionist = nutritionistService.findByPrincipal();
        curriculum.setNutritionist(nutritionist);
        save(curriculum);

    }

    public void modify(int id, Curriculum curr){
        userAccountService.assertRole("NUTRITIONIST");
        Nutritionist nutritionist = nutritionistService.findByPrincipal();
        Curriculum curriculum = findOne(id);
        curriculum.setEducational(curr.getEducational());
        curriculum.setExperience(curr.getExperience());
        curriculum.setHobbies(curr.getHobbies());
        curriculum.setReferences(curr.getReferences());

        save(curriculum);
    }
}
