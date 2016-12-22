package converters;

import domain.Attachment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AttachmentToStringConverter implements Converter<Attachment, String> {

    @Override
    public String convert(Attachment attachment) {
        String result;

        if (attachment == null)
            result = null;
        else
            result = String.valueOf(attachment.getId());

        return result;
    }

}
