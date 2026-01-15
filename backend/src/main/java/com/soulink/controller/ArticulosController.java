package com.soulink.controller;

import com.soulink.model.Articulo;
import com.soulink.service.ArticulosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos")
@RequiredArgsConstructor
public class ArticulosController {

    private final ArticulosService articulosService;

    @GetMapping
    public List<Articulo> listar() {
        return articulosService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        return articulosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Articulo crear(@RequestBody Articulo articulo) {
        return articulosService.guardar(articulo);
    }

    @PutMapping("/{id}")
    public Articulo actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        return articulosService.actualizar(id, articulo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        articulosService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
