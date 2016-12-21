package controllers.Administrator;

import domain.SpamTags;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;
import services.SpamTagsService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {


    @Autowired
    private AdministratorService administratorService;

}
