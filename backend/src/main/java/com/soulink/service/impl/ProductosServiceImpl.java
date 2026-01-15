package com.soulink.service.impl;

import com.soulink.model.Producto;
import com.soulink.repository.ProductosRepository;
import com.soulink.service.ProductosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductosServiceImpl implements ProductosService {

    private final ProductosRepository productosRepository;

    @Override
    public List<Producto> listarTodos() {
        return productosRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return productosRepository.findById(id);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productosRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        producto.setId(id);
        return productosRepository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        productosRepository.deleteById(id);
    }
}
