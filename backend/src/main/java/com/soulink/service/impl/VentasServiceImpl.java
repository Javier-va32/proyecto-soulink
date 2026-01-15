package com.soulink.service.impl;

import com.soulink.model.Venta;
import com.soulink.repository.VentasRepository;
import com.soulink.service.VentasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentasServiceImpl implements VentasService {

    private final VentasRepository ventasRepository;

    @Override
    public List<Venta> listarTodos() {
        return ventasRepository.findAll();
    }

    @Override
    public Optional<Venta> obtenerPorId(Long id) {
        return ventasRepository.findById(id);
    }

    @Override
    public Venta guardar(Venta venta) {
        return ventasRepository.save(venta);
    }

    @Override
    public Venta actualizar(Long id, Venta venta) {
        venta.setId(id);
        return ventasRepository.save(venta);
    }

    @Override
    public void eliminar(Long id) {
        ventasRepository.deleteById(id);
    }
}
