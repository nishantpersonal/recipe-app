package guru.springframework.recipedemo.controllers;

import guru.springframework.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.domain.Difficulty;
import guru.springframework.recipedemo.domain.Recipe;
import guru.springframework.recipedemo.exceptions.NotFoundException;
import guru.springframework.recipedemo.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

/**
 * Created by jt on 6/19/17.
 */
@Controller
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String addNewRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }
    @RequestMapping("/recipe/{id}/update")
    public String updateById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand recipeCommand1=recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/"+recipeCommand1.getId()+"/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipeById(id);
        return "redirect:/";
    }



}
