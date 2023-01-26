package com.UdeA.ciclo3.service;

import com.UdeA.ciclo3.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;
}
