package com.soulink.service;

import com.soulink.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductosService {
    List<Producto> listarTodos();
    Optional<Producto> obtenerPorId(Long id);
    Producto guardar(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
