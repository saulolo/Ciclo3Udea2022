package com.UdeA.ciclo3.repo;


import com.UdeA.ciclo3.modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientosRepository extends JpaRepository<MovimientoDinero, Integer> {

    // MÉTODO PARA FILTRAR MOVIMIENTOS POR EMPLEADO
    @Query(value = "select * from movimientos where empleado_id=?1",nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpleado (Integer id);


    // MÉTODO PARA FILTRAR MOVIMIENTOS POR EMPRESA
    @Query(value = "select * from movimientos where empleado_id in (select id from empleado where empresa_id=?202)",nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa (Integer id);


}
