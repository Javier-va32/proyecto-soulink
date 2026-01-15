package com.soulink.service.impl;

import com.soulink.model.Cliente;
import com.soulink.repository.ClientesRepository;
import com.soulink.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClientesRepository clientesRepository;

    @Override
    public List<Cliente> listarTodos() {
        return clientesRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long id) {
        return clientesRepository.findById(id);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {
        cliente.setId(id);
        return clientesRepository.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        clientesRepository.deleteById(id);
    }
}
