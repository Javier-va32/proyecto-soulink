package com.soulink.service;

import com.soulink.model.Rol;
import java.util.List;
import java.util.Optional;

public interface RolesService {
    List<Rol> listarTodos();
    Optional<Rol> obtenerPorId(Long id);
    Rol guardar(Rol rol);
    Rol actualizar(Long id, Rol rol);
    void eliminar(Long id);
}
