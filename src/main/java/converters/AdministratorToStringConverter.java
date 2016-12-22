package converters;

import domain.Administrator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AdministratorToStringConverter implements Converter<Administrator, String> {

    @Override
    public String convert(Administrator administrator) {
        String result;

        if (administrator == null)
            result = null;
        else
            result = String.valueOf(administrator.getId());

        return result;
    }

}
