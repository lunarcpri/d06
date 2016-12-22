package converters;

import domain.Quantity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class QuantityToStringConverter implements Converter<Quantity, String> {

    @Override
    public String convert(Quantity quantity) {
        String result;

        if (quantity == null)
            result = null;
        else
            result = String.valueOf(quantity.getId());

        return result;
    }

}
