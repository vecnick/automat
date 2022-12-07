package mirea.automat.controllers;


import mirea.automat.models.Position;
import mirea.automat.services.PositionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/positions")
public class PositionsController {
    private final PositionsService positionsService;

    public PositionsController(PositionsService positionsService) {
        this.positionsService = positionsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("positions", positionsService.findAll());
        return "positions/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("position", positionsService.findOne(id));
        return "positions/show";
    }

    @GetMapping("/new")
    public String newPosition(@ModelAttribute("position") Position position) {
        return "positions/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("position") @Valid Position position,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "positions/new";

        positionsService.save(position);
        return "redirect:/positions";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("position", positionsService.findOne(id));
        return "positions/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("position") @Valid Position position, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "positions/edit";

        positionsService.update(id, position);
        return "redirect:/positions";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        positionsService.delete(id);
        return "redirect:/positions";
    }
}