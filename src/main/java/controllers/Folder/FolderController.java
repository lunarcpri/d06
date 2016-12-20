package controllers.Folder;

import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Recipe;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.FolderService;
import services.RecipeService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/folder")
public class FolderController extends AbstractController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private FolderService folderService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView result;
        Actor principal = actorService.findActorByPrincipal();
        Collection<Folder> foldersCollection;

        foldersCollection = principal.getFolders();
        result = new ModelAndView("folder/list");
        result.addObject("folderList",foldersCollection);
        result.addObject("requestURI","folder/list.do");
        return result;
    }


    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(required=false) Folder folder) {

        return createEditModelAndView(folder,null);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("folder") Folder folder, BindingResult bindingResult) {
        ModelAndView result;
        Assert.notNull(folder);
        Actor principal = actorService.findActorByPrincipal();

        Assert.isTrue(principal.getFolders().contains(folder));

        if (bindingResult.hasErrors()){
            return createEditModelAndView(folder,"wrong");
        }
        result = new ModelAndView("folder/edit");
        result.addObject("folder",folder);

        return result;
    }

    protected ModelAndView createEditModelAndView(Folder folder, String message){
        ModelAndView result;
        Assert.notNull(folder);
        Actor principal = actorService.findActorByPrincipal();

        Assert.isTrue(principal.getFolders().contains(folder));


        result = new ModelAndView("folder/edit");
        result.addObject("folder",folder);
        result.addObject("folder",message);

        return  result;
    }
}
