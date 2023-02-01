package com.UdeA.ciclo3.service;

import com.UdeA.ciclo3.modelos.Empresa;
import com.UdeA.ciclo3.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;
    /*30. (continúa en el ProyectoConverTicShop) debo de crear toda la lista de empresas que el usuario puede ver y eso
    lo hago con el método getAll donde creo una lista con el nombre de la clase a la que se refiere, en este caso la de empresa.*/

    //MÉTODO PARA VER LAS EMPRESAS
    //Este método retornará la lista de empresas usando métodos heredados del JPARepository
    public List<Empresa> getAllEmpresas(){
        List<Empresa> empresaList = new ArrayList<>(); //31. Creo un objeto de tipo EmpresaReposity para poder usar los métodos de dicha clase.
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
        /*32. Necesito llenar esa lista con algo, y ese algo es las empresas que estén registradas en la base de datos
        y que estén guardadas em empresaRepositiry, eso lo hago llamando la clase del repository con el método .findAll,
        este método me devuelve un objeto iterable, debo de recorrerlo con un forEach y por cada empresa que se
        encontrara dentro de él, la agregara a esa lista que acabo de crear; y después la retorno.
        Eso hace que cuando desde la página principal yo quiera ver todas las empresas lo que yo debo de hacer es llamar
        al método que se llama en este caso getAllEmpresas() y asi la puedo ver en pantalla.*/
    }

    //MÉTODO PARA EDITAR UNA EMPRESA
    public Empresa getEmpresaById(Integer id){  //33. Creamos un método que me traiga el Id de la empresa, porque con este puedo hacer lo que necesite.
        return empresaRepository.findById(id).get(); //34. con findById(id).get() me traigo el id de la empresa.(si no le pongo el get, me trae es el espacio en memoria)
    }

    //MÉTODO PARA GUARDAR O ACTUALIZAR OBJETOS DE TIPO EMPRESA (guardar y actualizar viene siendo el mismo método)
    public boolean saveOrUpdateEmpresa(Empresa empresa){  //35. Método booleano porque me va a traer un false o un true (Trayendo el objeto Empresa)
        Empresa emp = empresaRepository.save(empresa);    //36. Creo un objeto temporal para verificar si la tarea se hizo o no.
        if(empresaRepository.findById(emp.getId())!=null) {//37. Utilizo el método save que es el que me va a guardar o actualizar la entidad.
            return true;     //38. Verifico con un condicional para saber si el objeto existe o no , y si si, que lo actualice.
        }
        return false;
    }

    //MÉTODO PARA ELIMINAR EMPRESAS (Elimina empresas registradas teniendo el id)
    public boolean deleteEmpresas(Integer id){  //39. Método booleano porque me va a traer un false o un true (Trayendo el objeto Id)
        empresaRepository.deleteById(id);  //40. Utilizo el método deleteById porque va a eliminar por ID, y es el que me va a eliminar entidad.
        if(getEmpresaById(id)!=null) {  //41. Verifico con un condicional para saber si el objeto se eliminó o no llamando el método que ya habíamos creado
            return false;
        }
        return true;
    }

}
