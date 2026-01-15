package com.soulink.service;

import com.soulink.model.Venta;
import java.util.List;
import java.util.Optional;

public interface VentasService {
    List<Venta> listarTodos();
    Optional<Venta> obtenerPorId(Long id);
    Venta guardar(Venta venta);
    Venta actualizar(Long id, Venta venta);
    void eliminar(Long id);
}
