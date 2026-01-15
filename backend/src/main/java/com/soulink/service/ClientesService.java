package com.soulink.service;

import com.soulink.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClientesService {
    List<Cliente> listarTodos();
    Optional<Cliente> obtenerPorId(Long id);
    Cliente guardar(Cliente cliente);      // ← singular Cliente
    Cliente actualizar(Long id, Cliente cliente); // ← singular Cliente
    void eliminar(Long id);
}
