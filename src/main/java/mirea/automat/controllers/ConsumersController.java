package mirea.automat.controllers;

import mirea.automat.models.Consumer;
import mirea.automat.services.ConsumersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/consumers")
public class ConsumersController {

    private final ConsumersService consumersService;

    public ConsumersController(ConsumersService consumersService) {
        this.consumersService = consumersService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("consumers", consumersService.findAll());
        return "consumers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("consumer", consumersService.findOne(id));
        return "consumers/show";
    }

    @GetMapping("/new")
    public String newConsumer(@ModelAttribute("consumer") Consumer consumer) {
        return "consumers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("consumer") @Valid Consumer consumer,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "consumers/new";

        consumersService.save(consumer);
        return "redirect:/consumers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("consumer", consumersService.findOne(id));
        return "consumers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("consumer") @Valid Consumer consumer, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "consumers/edit";

        consumersService.update(id, consumer);
        return "redirect:/consumers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        consumersService.delete(id);
        return "redirect:/consumers";
    }
}
