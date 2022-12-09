package mirea.automat.controllers;

import mirea.automat.models.Cloth;
import mirea.automat.services.ClothesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/clothes")
public class ClothesController {
    private final ClothesService clothesService;

    public ClothesController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "count", required = false) Integer clothesPerPage,
                        @RequestParam(value = "sort", required = false) boolean sortBySize) {
        if (page == null || clothesPerPage == null)
            model.addAttribute("clothes", clothesService.findAll(sortBySize));
        else
            model.addAttribute("clothes", clothesService.findWithPagination(page, clothesPerPage, sortBySize));
        return "clothes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("cloth", clothesService.findOne(id));
        model.addAttribute("orders", clothesService.getOrdersByClothId(id));
        return "clothes/show";
    }

    @GetMapping("/new")
    public String newTextile(@ModelAttribute("cloth") Cloth cloth) {
        return "clothes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("cloth") @Valid Cloth cloth,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "clothes/new";

        clothesService.save(cloth);
        return "redirect:/clothes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("cloth", clothesService.findOne(id));
        return "clothes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("cloth") @Valid Cloth cloth, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "clothes/edit";

        clothesService.update(id, cloth);
        return "redirect:/clothes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        clothesService.delete(id);
        return "redirect:/clothes";
    }
    @GetMapping("/search")
    public String searchPage() {
        return "clothes/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("clothes", clothesService.searchByName(query));
        return "clothes/search";}
}
