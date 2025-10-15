package com.empresix.gestion.repositorio;

import com.empresix.gestion.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepositorio {
    Empleado crear(Empleado empleado);
    Optional<Empleado> buscarPorId(Long id);
    Optional<Empleado> buscarPorCodigo(String codigo);
    List<Empleado> listarTodos();
    void eliminar(Long id);
}