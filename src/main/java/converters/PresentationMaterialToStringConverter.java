package converters;

import domain.PresentationMaterial;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PresentationMaterialToStringConverter implements Converter<PresentationMaterial, String> {

    @Override
    public String convert(PresentationMaterial presentationMaterial) {
        String result;

        if (presentationMaterial == null)
            result = null;
        else
            result = String.valueOf(presentationMaterial.getId());

        return result;
    }

}
