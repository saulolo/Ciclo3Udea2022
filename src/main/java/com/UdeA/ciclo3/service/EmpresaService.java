package com.UdeA.ciclo3.service;

import com.UdeA.ciclo3.modelos.Empresa;
import com.UdeA.ciclo3.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//Le decimos a Spring que esta clase es de servicios
@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    //MÉTODO PARA VER LAS EMPRESAS
    //Método que retornará la lista de empresas usando métodos heredados del jpaRepository
    public List<Empresa> getAllEmpresas(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));  //Recorremos el iterable que regresa el metodo findAll del Jpa y lo guardamos en la lista creada
        return empresaList;
    }

    //Metodo que me trae un objeto de tipo Empresa cuando cuento con el id de la misma
    public Empresa getEmpresaById(Integer id){
        return empresaRepository.findById(id).get();
    }

    //MÉTODO PARA GUARDAR O ACTUALIZAR OBJETOS DE TIPO EMPRESA (guardar y actualizar viene siendo el mismo método)
    //Método para guardar o actualizar objetos de tipo Empresa
    public boolean saveOrUpdateEmpresa(Empresa empresa){
        Empresa emp=empresaRepository.save(empresa);
        if (empresaRepository.findById(emp.getId())!=null){
            return true;
        }
        return false;
    }

    //MÉTODO PARA ELIMINAR EMPRESAS (Elimina empresas registradas teniendo el id)
    //Método para eliminar empresas registradas teniendo el id
    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);  //Eliminar

        if (empresaRepository.findById(id)!=null){  //Verificación del servicio eliminación
            return true;
        }
        return false;
    }

}
