package br.com.treinaweb.ediaristas.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.web.services.WebDiariaService;

@Controller
@RequestMapping("/admin/diarias")
public class DiariaController {

    @Autowired
    private WebDiariaService service;

    @GetMapping
    public ModelAndView buscarDarias() {
        var modelAndView = new ModelAndView("admin/diaria/listar");

        modelAndView.addObject("diarias", service.buscarDarias());

        return modelAndView;
    }

    @GetMapping("/{id}/pagar")
    public String pagar(@PathVariable Long id, HttpServletRequest request) {
        service.pagar(id);
        var rotaAnterior = request.getHeader("Referer");
        return "redirect:" + rotaAnterior;
    }

}
