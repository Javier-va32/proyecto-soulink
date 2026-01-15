package com.soulink.service.impl;

import com.soulink.model.Articulo;
import com.soulink.repository.ArticulosRepository;
import com.soulink.service.ArticulosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticulosServiceImpl implements ArticulosService {

    private final ArticulosRepository articulosRepository;

    @Override
    public List<Articulo> listarTodos() {
        return articulosRepository.findAll();
    }

    @Override
    public Optional<Articulo> obtenerPorId(Long id) {
        return articulosRepository.findById(id);
    }

    @Override
    public Articulo guardar(Articulo articulo) {
        return articulosRepository.save(articulo);
    }

    @Override
    public Articulo actualizar(Long id, Articulo articulo) {
        articulo.setId(id);
        return articulosRepository.save(articulo);
    }

    @Override
    public void eliminar(Long id) {
        articulosRepository.deleteById(id);
    }
}
