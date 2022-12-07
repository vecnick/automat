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

    private final DefectsService staffsService;

    public DefectsController(DefectsService staffsService) {
        this.staffsService = staffsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("defects", staffsService.findAll());
        return "defects/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("defect", staffsService.findOne(id));
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

        staffsService.save(defect);
        return "redirect:/defects";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("defect", staffsService.findOne(id));
        return "defects/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("defect") @Valid Defect defect, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "defects/edit";

        staffsService.update(id, defect);
        return "redirect:/defects";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        staffsService.delete(id);
        return "redirect:/defects";
    }
}
