package converters;

import domain.Contest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ContestRepository;

@Component
@Transactional
public class StringToContestConverter implements Converter<String, Contest> {

    @Autowired
    ContestRepository contestRepository;

    @Override
    public Contest convert(String text) {
        Contest result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = contestRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }

}
