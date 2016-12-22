package converters;

import domain.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.SponsorRepository;

@Component
@Transactional
public class StringToSponsorConverter implements Converter<String, Sponsor> {

    @Autowired
    SponsorRepository sponsorRepository;

    @Override
    public Sponsor convert(String text) {
        Sponsor result;
        int id;

        try {
            id = Integer.valueOf(text);
            result = sponsorRepository.findOne(id);
        } catch (Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }

}
