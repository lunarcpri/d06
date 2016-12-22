package converters;

import domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.BillRepository;

@Component
@Transactional
public class StringToBillConverter implements Converter<String, Bill> {

    @Autowired
    BillRepository billRepository;

    @Override
    public Bill convert(String text) {
        Bill result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = billRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }

}
