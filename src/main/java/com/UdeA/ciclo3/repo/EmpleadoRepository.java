package com.UdeA.ciclo3.repo;

import com.UdeA.ciclo3.modelos.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmpleadoRepository  extends CrudRepository<Empleado, Integer> {
    @Query(value="SELECT * FROM empleado where empresa_id= ?1", nativeQuery=true)
    ////@Query(value="SELECT * from movimiento_dinero where empleado_1 in(select id from empleado where empresa_id= ?1)", nativeQuery=true)
    //Ese es el query de  los movimientos de los empleados que pertenecen a una empresa

    public abstract ArrayList<Empleado> findByEmpresa(Integer id);

}
