package com.soulink.service;

import com.soulink.model.Compra;
import java.util.List;
import java.util.Optional;

public interface ComprasService {
    List<Compra> listarTodos();
    Optional<Compra> obtenerPorId(Long id);
    Compra guardar(Compra compra);
    Compra actualizar(Long id, Compra compra);
    void eliminar(Long id);
}
