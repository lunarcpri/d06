package converters;

import domain.Nutritionist;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class NutritionistToStringConverter implements Converter<Nutritionist, String> {

    @Override
    public String convert(Nutritionist nutritionist) {
        String result;

        if (nutritionist == null)
            result = null;
        else
            result = String.valueOf(nutritionist.getId());

        return result;
    }

}
