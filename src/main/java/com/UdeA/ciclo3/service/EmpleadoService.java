package com.UdeA.ciclo3.service;

import com.UdeA.ciclo3.modelos.Empleado;
import com.UdeA.ciclo3.modelos.Empresa;
import com.UdeA.ciclo3.repo.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;


    //MÉTODO PARA VER LOS EMPLEADOS
    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }


    //MÉTODO PARA BUSCAR EMPLEADO POR ID
    public Optional<Empleado> getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id);
    }

    //MÉTODO PARA BUSCAR POR EMPLEADO POR EMPRESA
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return empleadoRepository.findByEmpresa(id);
    }


    //MÉTODO PARA GUARDAR O ACTUALIZAR OBJETOS DE TIPO EMPLEADO
    public Empleado saveOrUpdateEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    //MÉTODO PARA ELIMINAR EMPLEADO (Elimina empleados registrados teniendo el id)
    public boolean deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
        if (this.empleadoRepository.findById(id).isPresent()) {
            return false;
        }
        return true;
    }


}
