package org.iesalixar.daw2.danielquintero.crudpartediario.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.danielquintero.crudpartediario.dtos.ParteDiarioDTO;
import org.iesalixar.daw2.danielquintero.crudpartediario.exceptions.InvalidParteDiarioException;
import org.iesalixar.daw2.danielquintero.crudpartediario.services.EncargadoService;
import org.iesalixar.daw2.danielquintero.crudpartediario.services.ParteDiarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/partes")
@RequiredArgsConstructor
public class ParteDiarioController {
    private static final List<String> ESTADOS = List.of("PENDIENTE", "REVISADO", "FINALIZADO");

    private final ParteDiarioService parteDiarioService;
    private final EncargadoService encargadoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("partes", parteDiarioService.findAll());
        return "partes/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("parte", parteDiarioService.findById(id));
        return "partes/detail";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model) {
        model.addAttribute("parteDiario", new ParteDiarioDTO());
        prepararFormulario(model);
        model.addAttribute("modoEdicion", false);
        return "partes/form";
    }

    @PostMapping("/nuevo")
    public String create(@Valid @ModelAttribute("parteDiario") ParteDiarioDTO dto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepararFormulario(model);
            model.addAttribute("modoEdicion", false);
            return "partes/form";
        }
        try {
            parteDiarioService.create(dto);
        } catch (InvalidParteDiarioException ex) {
            bindingResult.reject("parteDiario.invalid", ex.getMessage());
            prepararFormulario(model);
            model.addAttribute("modoEdicion", false);
            return "partes/form";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Parte diario creado correctamente.");
        return "redirect:/partes";
    }

    @GetMapping("/{id}/editar")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("parteDiario", parteDiarioService.findById(id));
        prepararFormulario(model);
        model.addAttribute("modoEdicion", true);
        return "partes/form";
    }

    @PostMapping("/{id}/editar")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("parteDiario") ParteDiarioDTO dto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        dto.setId(id);
        if (bindingResult.hasErrors()) {
            prepararFormulario(model);
            model.addAttribute("modoEdicion", true);
            return "partes/form";
        }
        try {
            parteDiarioService.update(id, dto);
        } catch (InvalidParteDiarioException ex) {
            bindingResult.reject("parteDiario.invalid", ex.getMessage());
            prepararFormulario(model);
            model.addAttribute("modoEdicion", true);
            return "partes/form";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Parte diario actualizado correctamente.");
        return "redirect:/partes";
    }

    @PostMapping("/{id}/eliminar")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        parteDiarioService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Parte diario eliminado correctamente.");
        return "redirect:/partes";
    }

    private void prepararFormulario(Model model) {
        model.addAttribute("encargados", encargadoService.findAll());
        model.addAttribute("estados", ESTADOS);
    }
}
