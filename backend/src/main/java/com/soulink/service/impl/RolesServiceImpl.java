package com.soulink.service.impl;

import com.soulink.model.Rol;
import com.soulink.repository.RolesRepository;
import com.soulink.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Override
    public List<Rol> listarTodos() {
        return rolesRepository.findAll();
    }

    @Override
    public Optional<Rol> obtenerPorId(Long id) {
        return rolesRepository.findById(id);
    }

    @Override
    public Rol guardar(Rol rol) {
        return rolesRepository.save(rol);
    }

    @Override
    public Rol actualizar(Long id, Rol rol) {
        rol.setId(id);
        return rolesRepository.save(rol);
    }

    @Override
    public void eliminar(Long id) {
        rolesRepository.deleteById(id);
    }
}
