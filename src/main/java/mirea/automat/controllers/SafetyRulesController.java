package mirea.automat.controllers;

import mirea.automat.models.SafetyRule;
import mirea.automat.models.Staff;
import mirea.automat.services.SafetyRulesService;
import mirea.automat.services.StaffsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/safetyRules")
public class SafetyRulesController {
    private final SafetyRulesService safetyRulesService;

    public SafetyRulesController(SafetyRulesService safetyRulesService) {
        this.safetyRulesService = safetyRulesService;
    }
    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer safetyRulesPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortByRuleName) {
        if (page == null || safetyRulesPerPage == null)
            model.addAttribute("safetyRules", safetyRulesService.findAll(sortByRuleName));
        else
            model.addAttribute("safetyRules", safetyRulesService.findWithPagination(page, safetyRulesPerPage, sortByRuleName));
        return "safetyRules/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("safetyRule", safetyRulesService.findOne(id));
        model.addAttribute("staffs", safetyRulesService.getStaffsBySafetyRuleId(id));
        return "safetyRules/show";
    }

    @GetMapping("/new")
    public String newSafetyRule(@ModelAttribute("safetyRule")SafetyRule safetyRule) {
        return "safetyRules/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("safetyRule") @Valid SafetyRule safetyRule,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "safetyRules/new";

        safetyRulesService.save(safetyRule);
        return "redirect:/safetyRules";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("safetyRule", safetyRulesService.findOne(id));
        return "safetyRules/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("safetyRule") @Valid SafetyRule safetyRule, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "safetyRules/edit";

        safetyRulesService.update(id, safetyRule);
        return "redirect:/safetyRules";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        safetyRulesService.delete(id);
        return "redirect:/safetyRules";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "safetyRules/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("safetyRules", safetyRulesService.searchByName(query));
        return "safetyRules/search";
    }
}
