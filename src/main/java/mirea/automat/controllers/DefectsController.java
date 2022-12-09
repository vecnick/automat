package mirea.automat.controllers;

import mirea.automat.models.Defect;
import mirea.automat.services.DefectsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/defects")
public class DefectsController {

    private final DefectsService defectsService;

    public DefectsController(DefectsService defectsService) {
        this.defectsService = defectsService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer defectsPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByDefectCase) {
        if (page == null || defectsPerPage == null)
            model.addAttribute("defects", defectsService.findAll(sortByDefectCase));
        else
            model.addAttribute("defects", defectsService.findWithPagination(page, defectsPerPage, sortByDefectCase));
        return "defects/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("defect", defectsService.findOne(id));
        model.addAttribute("qualities", defectsService.getQualitiesByDefectId(id));
        return "defects/show";
    }

    @GetMapping("/new")
    public String newDefect(@ModelAttribute("defect") Defect defect) {
        return "defects/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("defect") @Valid Defect defect,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "defects/new";

        defectsService.save(defect);
        return "redirect:/defects";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("defect", defectsService.findOne(id));
        return "defects/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("defect") @Valid Defect defect, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "defects/edit";

        defectsService.update(id, defect);
        return "redirect:/defects";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        defectsService.delete(id);
        return "redirect:/defects";
    }
    @GetMapping("/search")
    public String searchPage() {
        return "defects/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("defects", defectsService.searchByName(query));
        return "defects/search";}
}
