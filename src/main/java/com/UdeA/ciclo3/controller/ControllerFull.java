package com.UdeA.ciclo3.controller;

import com.UdeA.ciclo3.modelos.Empresa;
import com.UdeA.ciclo3.service.EmpresaService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;

    //Ver Jsonn de todas las empresas
    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas() {
        return empresaService.getAllEmpresas();
    }

    //Guardar el Json del body como una nueva empresa o registro en nuestra BD
    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp) {
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    //Ver el Json de una empresa por ID
    @GetMapping(path = "enterprises/{id}") //con path o sin él igual funciona.
    public Empresa empresaPorId(@PathVariable("id") Integer id) {
        return this.empresaService.getEmpresaById(id);
    }

    //Actualizar el Json de una empresa por sus atributos
    @PatchMapping("enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa) {
        Empresa emp = empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return empresaService.saveOrUpdateEmpresa(emp);
    }

    //Elimina registros de la BD
    @DeleteMapping(path = "enterprises/{id}")
    public String deleteEmpresa(@PathVariable("id")Integer id){
        boolean respuesta = this.empresaService.deleteEmpresa(id);
        if(respuesta){  //si respuesta es true
            return "Se elimino con id " + id;
        }
        else {
            return "No se puede eliminar la empresa con id " + id;
        }
    }
}
