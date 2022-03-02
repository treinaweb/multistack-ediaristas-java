package br.com.treinaweb.ediaristas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.treinaweb.ediaristas.web.dtos.FlashMessage;
import br.com.treinaweb.ediaristas.web.dtos.ResetarSenhaForm;
import br.com.treinaweb.ediaristas.web.services.WebResetarSenhaService;

@Controller
@RequestMapping("/admin/resetar-senha")
public class ResetarSenhaController {

    @Autowired
    private WebResetarSenhaService service;

    @GetMapping
    public ModelAndView resetarSenha() {
        var modelAndView = new ModelAndView("admin/auth/resetar-senha");

        modelAndView.addObject("form", new ResetarSenhaForm());

        return modelAndView;
    }

    @PostMapping
    public String resetarSenha(@ModelAttribute ResetarSenhaForm form, RedirectAttributes attrs) {
        service.solictarResetDeSenha(form);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Verifique o seu e-mail para ter acesso ao link de reset de senha"));
        return "redirect:/admin/resetar-senha";
    }

}
