package converters;

import domain.SocialIdentity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SocialIdentityToStringConverter implements Converter<SocialIdentity, String> {

    @Override
    public String convert(SocialIdentity socialIdentity) {
        String result;

        if (socialIdentity == null)
            result = null;
        else
            result = String.valueOf(socialIdentity.getId());

        return result;
    }

}
