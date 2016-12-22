/* WelcomeController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.UserOrNutritionist;

import controllers.AbstractController;
import domain.Recipe;
import domain.UserOrNutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.RecipeService;
import services.UserOrNutritionistService;

import java.util.Collection;

@Controller
@RequestMapping("/userornutritionist")
public class UserOrNutritionistController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public UserOrNutritionistController() {
		super();
	}

	@Autowired
	RecipeService recipeService;

	@Autowired
	UserOrNutritionistService userOrNutritionistService;

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView index() {
		ModelAndView result;
		Collection<Recipe> recipeCollection;

		result = new ModelAndView("userornutritionist/list");
		recipeCollection = userOrNutritionistService.findAllRecipesByFollowingActors();
		result.addObject("recipes", recipeCollection);
		result.addObject("requestURI","userornutritionist/list.do");

		return result;
	}


}