package services;

import domain.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ReferenceRepository;

@Service
@Transactional
public class ReferenceService {

    @Autowired
    private ReferenceRepository referenceRepository;


    public ReferenceService() {
        super();
    }

    public Reference save(Reference reference) {
        Assert.notNull(reference);

        return referenceRepository.save(reference);
    }
}
