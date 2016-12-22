package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class BasicController {

    @RequestMapping(value = "/register")
    public ModelAndView index() {
        ModelAndView result;

        result = new ModelAndView("welcome/register");

        return result;
    }

}
