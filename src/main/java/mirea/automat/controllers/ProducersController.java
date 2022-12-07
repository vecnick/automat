package mirea.automat.controllers;


import mirea.automat.models.Producer;
import mirea.automat.services.ProducersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/producers")
public class ProducersController {
    private final ProducersService producersService;

    public ProducersController(ProducersService producersService) {
        this.producersService = producersService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("producers", producersService.findAll());
        return "producers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("producer", producersService.findOne(id));
        return "producers/show";
    }

    @GetMapping("/new")
    public String newProducer(@ModelAttribute("producer") Producer producer) {
        return "producers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("producer") @Valid Producer producer,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "producers/new";

        producersService.save(producer);
        return "redirect:/producers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("producer", producersService.findOne(id));
        return "producers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("producer") @Valid Producer producer, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "producers/edit";

        producersService.update(id, producer);
        return "redirect:/producers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        producersService.delete(id);
        return "redirect:/producers";
    }
}