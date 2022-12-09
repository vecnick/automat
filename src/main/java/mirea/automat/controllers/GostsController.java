package mirea.automat.controllers;


import mirea.automat.models.Gost;
import mirea.automat.services.GostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/gosts")
public class GostsController {

    private final GostsService gostsService;

    public GostsController(GostsService gostsService) {
        this.gostsService = gostsService;
    }
    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer gostsPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByName) {
        if (page == null || gostsPerPage == null)
            model.addAttribute("gosts", gostsService.findAll(sortByName));
        else
            model.addAttribute("gosts", gostsService.findWithPagination(page, gostsPerPage, sortByName));
        return "gosts/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("gost", gostsService.findOne(id));
        model.addAttribute("qualities", gostsService.getQualitiesByGostId(id));
        model.addAttribute("clothes", gostsService.getClothesByGostId(id));
        return "qualities/show";
    }


    @GetMapping("/new")
    public String newStaff(@ModelAttribute("gost") Gost gost) {
        return "gosts/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("gost") @Valid Gost gost,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "gosts/new";

        gostsService.save(gost);
        return "redirect:/gosts";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("gost", gostsService.findOne(id));
        return "gosts/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("gost") @Valid Gost gost, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "gosts/edit";

        gostsService.update(id, gost);
        return "redirect:/gosts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        gostsService.delete(id);
        return "redirect:/gosts";
    }
    @GetMapping("/search")
    public String searchPage() {
        return "gosts/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("gosts", gostsService.searchByName(query));
        return "gosts/search";}
}

