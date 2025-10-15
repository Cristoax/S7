package com.empresix.gestion.repositorio;

import com.empresix.gestion.entity.Cargo;
import java.util.List;
import java.util.Optional;

public interface CargoRepositorio {
    Cargo crear(Cargo cargo);
    Cargo actualizar(Cargo cargo);
    void eliminar(Long id);
    Optional<Cargo> buscarPorId(Long id);
    Optional<Cargo> buscarPorCodigo(String codigo);
    List<Cargo> listarTodos();
}