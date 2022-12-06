package mirea.automat.controllers;

import mirea.automat.models.Staff;
import mirea.automat.services.StaffsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/staffs")
public class StaffsController {
    private final StaffsService staffsService;

    public StaffsController(StaffsService staffsService) {
        this.staffsService = staffsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("staffs", staffsService.findAll());
        return "staffs/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("staff", staffsService.findOne(id));
        return "staffs/show";
    }

    @GetMapping("/new")
    public String newStaff(@ModelAttribute("staff") Staff staff) {
        return "staffs/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("staff") @Valid Staff staff,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "staffs/new";

        staffsService.save(staff);
        return "redirect:/staffs";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("staff", staffsService.findOne(id));
        return "staffs/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("staff") @Valid Staff staff, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "staffs/edit";

        staffsService.update(id, staff);
        return "redirect:/staffs";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        staffsService.delete(id);
        return "redirect:/staffs";
    }
}
