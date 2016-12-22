package controllers.Folder;

import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.FolderService;

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
        result.addObject("folderList", foldersCollection);
        result.addObject("requestURI", "folder/list.do");
        return result;
    }


    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam Folder folderId) {
        return createEditModelAndView(folderId, null);
    }

    @RequestMapping(value = "/new")
    public ModelAndView newFolder() {
        return createNewModelAndView(new Folder(), null);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, params = "send")
    public ModelAndView newFolderPost(@Valid @ModelAttribute("folder") Folder folder, BindingResult bindingResult) {
        Assert.notNull(folder);
        Actor principal = actorService.findActorByPrincipal();
        folder.setActor(principal);
        folder.setFolderType(Folder.FolderType.CUSTOM);
        if (bindingResult.hasErrors()) {
            return createNewModelAndView(folder, "wrong");
        }
        try {
            folderService.edit(folder);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            return createNewModelAndView(folder, "wrong");
        }
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteFolder(@RequestParam Folder folderId) {
        Assert.notNull(folderId);
        Actor principal = actorService.findActorByPrincipal();
        Assert.isTrue(principal.getFolders().contains(folderId));
        try {
            folderId.setActor(null);
            folderService.delete(folderId);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable throwable) {
            System.out.print(throwable.toString());
            return new ModelAndView("redirect:list.do");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("folder") Folder folder, BindingResult bindingResult) {
        Assert.notNull(folder);
        if (bindingResult.hasErrors()) {
            return createEditModelAndView(folder, "wrong");
        }
        try {
            folderService.edit(folder);
            return new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            System.out.println(oops.getMessage());
            return createEditModelAndView(folder, "wrong");
        }
    }

    protected ModelAndView createEditModelAndView(Folder folder, String message) {
        ModelAndView result;
        Assert.notNull(folder);

        result = new ModelAndView("folder/edit");
        result.addObject("folder", folder);
        result.addObject("message", message);

        return result;
    }


    protected ModelAndView createNewModelAndView(Folder folder, String message) {
        ModelAndView result;
        Assert.notNull(folder);

        result = new ModelAndView("folder/new");
        result.addObject("folder", folder);
        result.addObject("message", message);

        return result;
    }
}
