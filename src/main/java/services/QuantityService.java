package services;

import domain.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.QuantityRepository;

@Service
@Transactional
public class QuantityService {

    @Autowired
    private QuantityRepository quantityRepository;


    public QuantityService() {
        super();
    }

    public Quantity save(Quantity quantity) {
        Assert.notNull(quantity);

        return quantityRepository.save(quantity);
    }
}
