package com.soulink.service.impl;

import com.soulink.model.Compra;
import com.soulink.repository.ComprasRepository;
import com.soulink.service.ComprasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComprasServiceImpl implements ComprasService {

    private final ComprasRepository comprasRepository;

    @Override
    public List<Compra> listarTodos() {
        return comprasRepository.findAll();
    }

    @Override
    public Optional<Compra> obtenerPorId(Long id) {
        return comprasRepository.findById(id);
    }

    @Override
    public Compra guardar(Compra compra) {
        return comprasRepository.save(compra);
    }

    @Override
    public Compra actualizar(Long id, Compra compra) {
        compra.setId(id);
        return comprasRepository.save(compra);
    }

    @Override
    public void eliminar(Long id) {
        comprasRepository.deleteById(id);
    }
}
