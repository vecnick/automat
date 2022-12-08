package mirea.automat.controllers;

import mirea.automat.models.Textile;
import mirea.automat.services.TextilesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/textiles")
public class TextilesController {
    private final TextilesService textilesService;

    public TextilesController(TextilesService textilesService) {
        this.textilesService = textilesService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("textiles", textilesService.findAll());
        return "textiles/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("textile", textilesService.findOne(id));
        return "textiles/show";
    }

    @GetMapping("/new")
    public String newTextile(@ModelAttribute("textile") Textile textile) {
        return "textiles/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("textile") @Valid Textile textile,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "textiles/new";

        textilesService.save(textile);
        return "redirect:/textiles";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("textile", textilesService.findOne(id));
        return "textiles/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("textile") @Valid Textile textile, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "textiles/edit";

        textilesService.update(id, textile);
        return "redirect:/textiles";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        textilesService.delete(id);
        return "redirect:/textiles";
    }
    @GetMapping("/search")
    public String searchPage() {
        return "textiles/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("textiles", textilesService.searchByName(query));
        return "textiles/search";
    }
}
