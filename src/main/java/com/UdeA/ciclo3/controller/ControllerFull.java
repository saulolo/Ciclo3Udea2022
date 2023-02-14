package com.UdeA.ciclo3.controller;

import com.UdeA.ciclo3.modelos.Empleado;
import com.UdeA.ciclo3.modelos.Empresa;
import com.UdeA.ciclo3.modelos.MovimientoDinero;
import com.UdeA.ciclo3.service.EmpleadoService;
import com.UdeA.ciclo3.service.EmpresaService;
import com.UdeA.ciclo3.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerFull {

                                         /* SERVICIOS EMPRESAS */
    @Autowired
    EmpresaService empresaService;

    //VER Json de todas las empresas
    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas() {
        return empresaService.getAllEmpresas();
    }

    //GUARDAR el Json del body como una nueva empresa o registro en nuestra BD
    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp) {
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    //BUSCAR el Json de una empresa por ID
    @GetMapping(path = "/enterprises/{id}") //con path o sin él igual funciona.
    public Empresa empresaPorId(@PathVariable("id") Integer id) {
        return this.empresaService.getEmpresaById(id);
    }

    //ACTUALIZAR el Json de una empresa por sus atributos
    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa) {
        Empresa emp = empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return empresaService.saveOrUpdateEmpresa(emp);
    }

    //ELIMINAR los registros de la BD
    @DeleteMapping(path = "/enterprises/{id}")
    public String deleteEmpresa(@PathVariable("id")Integer id){
        boolean respuesta = this.empresaService.deleteEmpresa(id);
        if(respuesta){  //si respuesta es true
            return "Se elimino con id " + id;
        }
        else {
            return "No se puede eliminar la empresa con id " + id;
        }
    }



    /*--------------------------------------------------------------------------------------*/
                                    /* SERVICIOS EMPLEADOS */
    @Autowired
    EmpleadoService empleadoService;

    //SERVICIO VER EMPLEADOS
    @GetMapping("/empleados")
    public List<Empleado> verEmpleado(){ //Ver Json de todos los empleados
        return empleadoService.getAllEmpleado();
    }

    //SERVICIO GUARDAR EMPLEADOS
    @PostMapping("/empleados")
    public Optional<Empleado> guardarEmpleado(@RequestBody Empleado empl) {
        return Optional.ofNullable(this.empleadoService.saveOrUpdateEmpleado(empl))  ;
    }

    //SERVICIO BUSCAR/VER EMPLEADOS POR ID
    @GetMapping(path = "/empleados/{id}")
    public Optional<Empleado> empleadoPorId(@PathVariable("id") Integer id) {
        return this.empleadoService.getEmpleadoById(id);
    }

    //ACTUALIZAR EL JSON EMPLEADOS POR SUS ATRIBUTOS
    @PatchMapping("/empleados/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado) {
        Empleado empl = empleadoService.getEmpleadoById(id).get();
        empl.setNombre(empleado.getNombre());
        empl.setCorreo(empleado.getCorreo());
        empl.setEmpresa(empleado.getEmpresa());
        empl.setRol(empl.getRol());
        return empleadoService.saveOrUpdateEmpleado(empleado);
    }

    //ELIMINAR EMPLEADOS DE LA BD
    @DeleteMapping("/empleados/{id}") //Método para eliminar empleados por id
    public String DeleteEmpleado(@PathVariable("id")Integer id){
        boolean respuesta = empleadoService.deleteEmpleado(id); //Eliminamos usando el servicio de nuestro service
        if(respuesta){  //Si la respuesta es booleana es true, si se eliminó
            return "Se pudo eliminar correctamente el empleado con id " + id;
        }
        else {  //Si la respuesta es booleana es false, no si se eliminó
            return "No se puede eliminar la correctamente el empleado con id " + id;
        }
    }

    //SERVICIO BUSCAR/VER EMPLEADOS POR EMPRESA
    @GetMapping("/enterprises/{id}/empleados")// Consultar empleados por empresa
    public ArrayList<Empleado> EmpleadoPorEmpresa(@PathVariable("id") Integer id){
        return this.empleadoService.obtenerPorEmpresa(id);
    }


    /*--------------------------------------------------------------------------------------*/
    /* SERVICIOS MOVIMIENTOS */
    @Autowired
    MovimientosService movimientosService;


    //SERVICIO VER/CONSULTAR MOVIMIENTOS
    @GetMapping("/movimientos")
    public List<MovimientoDinero> verMovimientos(){ //Ver Json de todos los movimientos
        return movimientosService.getAllMovimientos();
    }


    //SERVICIO GUARDAR MOVIMIENTOS
    @PostMapping("/movimientos")
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimiento) {
        return movimientosService.saveOrUpdateMovimientos(movimiento);
    }

    //SERVICIO BUSCAR/VER MOVIMIENTOS POR ID
    @GetMapping(path = "/movimientos/{id}")
    public MovimientoDinero movimientoPorId(@PathVariable("id") Integer id) {
        return movimientosService.getMovimientoById(id);
    }

    //ACTUALIZAR EL JSON MOVIMIENTOS POR SUS ATRIBUTOS
    @PatchMapping("/movimientos/{id}")
    public MovimientoDinero actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimiento) {
        MovimientoDinero mov = movimientosService.getMovimientoById(id);
        mov.setConcepto(mov.getConcepto());
        mov.setMonto(mov.getMonto());
        mov.setUsuario(mov.getUsuario());
        return movimientosService.saveOrUpdateMovimientos(mov);
    }

    //ELIMINAR los registros de la BD
    @DeleteMapping(path = "/movimientos/{id}")
    public String deleteMovimiento(@PathVariable("id")Integer id){
        boolean respuesta = this.movimientosService.deleteMovimientos(id);
        if(respuesta){
            return "Se elimino correctamente  el movimiento con el id " + id;
        }
        else {
            return "No se puedo eliminar el movimiento con id " + id;
        }
    }

    //SERVICIO BUSCAR/VER MOVIMIENTOS POR ID EMPLEADOS
    @GetMapping("/empleados/{id}/movimientos")// Consultar movimientos por id del empleado
    public ArrayList<MovimientoDinero> MovimientoPorEmpleado(@PathVariable("id") Integer id){
        return movimientosService.obtenerPorEmpleado(id);
    }


    //SERVICIO BUSCAR/VER MOVIMIENTOS POR ID EMPRESAS
    @GetMapping("/empleados/{id}/enterprises")// Consultar movimientos por id del empresa
    public ArrayList<MovimientoDinero> MovimientoPorEmpresa(@PathVariable("id") Integer id){
        return movimientosService.obtenerPorEmpresa(id);
    }











}
