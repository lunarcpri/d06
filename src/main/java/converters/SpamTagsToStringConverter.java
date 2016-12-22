package converters;

import domain.SpamTags;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SpamTagsToStringConverter implements Converter<SpamTags, String> {

    @Override
    public String convert(SpamTags spamTags) {
        String result;

        if (spamTags == null)
            result = null;
        else
            result = String.valueOf(spamTags.getId());

        return result;
    }

}
