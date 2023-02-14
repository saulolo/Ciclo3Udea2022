package com.UdeA.ciclo3.service;

import com.UdeA.ciclo3.modelos.Empresa;
import com.UdeA.ciclo3.modelos.MovimientoDinero;
import com.UdeA.ciclo3.repo.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientosService {
    @Autowired
    MovimientosRepository movimientosRepository;

    /* -- SERVICIOS BÁSICOS -- */

    //MÉTODO PARA VER LOS MOVIMIENTOS
    //Me permite ver todos los movimientos sin ningún filtro
    public List<MovimientoDinero> getAllMovimientos(){
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientosRepository.findAll().forEach(movimientos -> movimientosList.add(movimientos));
        return movimientosList;
    }

    //MÉTODO QUE ME TRAE LOS MOVIMIENTOS POR ID
    public MovimientoDinero getMovimientoById(Integer id){
        return movimientosRepository.findById(id).get();
    }

    //MÉTODO PARA GUARDAR O ACTUALIZAR MOVIMIENTOS
    public MovimientoDinero saveOrUpdateMovimientos(MovimientoDinero movimientoDinero){
        MovimientoDinero mov = movimientosRepository.save(movimientoDinero);
        return mov;
    }

    //MÉTODO PARA ELIMINAR MOVIMIENTOS (Elimina movimientos registrados teniendo el id)
    public boolean deleteMovimientos(Integer id){
        movimientosRepository.deleteById(id);  //Elimina usando el método que nos ofrece el repositorio
        if (this.movimientosRepository.findById(id).isPresent()){  //Verificación del servicio eliminación
            return false; //Si al buscar el movimiento lo encontramos, no lo eliminó
        }
        return true;  //Si al buscar el movimiento no lo encontramos, si lo eliminó
    }


    /* -- SERVICIOS DE ESPECIALES DE LOS MOVIMIENTOS -- */

    //MÉTODO PARA VER LOS MOVIMIENTOS DESDE EL ID DE LOS EMPLEADOS
    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id){  //Obtener movimientos teniendo en cuenta el id de los empleados
        return movimientosRepository.findByEmpleado(id);
    }

    //MÉTODO PARA VER LOS MOVIMIENTOS DESDE EL ID DE LA EMPRESA
    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id){  //Obtener movimientos teniendo en cuenta el id de la empresa a la que pertenecen los empleados que se registraron
        return movimientosRepository.findByEmpleado(id);
    }






}
