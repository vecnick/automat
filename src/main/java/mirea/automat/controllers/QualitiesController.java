package mirea.automat.controllers;

import mirea.automat.models.Quality;
import mirea.automat.services.QualitiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/qualities")
public class QualitiesController {

    private final QualitiesService qualitiesService;

    public QualitiesController(QualitiesService qualitiesService) {
        this.qualitiesService = qualitiesService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("qualities", qualitiesService.findAll());
        return "qualities/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("quality", qualitiesService.findOne(id));
        return "qualities/show";
    }

    @GetMapping("/new")
    public String newQuality(@ModelAttribute("quality") Quality quality) {
        return "qualities/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("quality") @Valid Quality quality,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "qualities/new";

        qualitiesService.save(quality);
        return "redirect:/qualities";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("quality", qualitiesService.findOne(id));
        return "qualities/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("quality") @Valid Quality quality, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "qualities/edit";

        qualitiesService.update(id, quality);
        return "redirect:/qualities";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        qualitiesService.delete(id);
        return "redirect:/qualities";
    }
}