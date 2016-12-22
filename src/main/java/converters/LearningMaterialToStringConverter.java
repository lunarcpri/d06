package converters;

import domain.LearningMaterial;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LearningMaterialToStringConverter implements Converter<LearningMaterial, String> {

    @Override
    public String convert(LearningMaterial learningMaterial) {
        String result;

        if (learningMaterial == null)
            result = null;
        else
            result = String.valueOf(learningMaterial.getId());

        return result;
    }

}
