package com.UdeA.ciclo3.controller;


import com.UdeA.ciclo3.modelos.Empresa;
import com.UdeA.ciclo3.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;

    //SERVICIO VER EMPRESA
    @GetMapping ({"/","/VerEmpresas"})
    public String viewEmpresas(Model model){
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("empList", listaEmpresas);
        return "VerEmpresas";
    }

    //SERVICIO AGREGAR EMPRESA
    @GetMapping ({"/AgregarEmpresa"})
    public String nuevaEmpresa(Model model){
        Empresa emp = new Empresa();
        model.addAttribute("emp",emp);
        return "agregarEmpresa";
    }




}
