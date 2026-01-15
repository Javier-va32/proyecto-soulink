package com.soulink.controller;

import com.soulink.model.Compra;
import com.soulink.service.ComprasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class ComprasController {

    private final ComprasService comprasService;

    @GetMapping
    public List<Compra> listar() {
        return comprasService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerPorId(@PathVariable Long id) {
        return comprasService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Compra crear(@RequestBody Compra compra) {
        return comprasService.guardar(compra);
    }

    @PutMapping("/{id}")
    public Compra actualizar(@PathVariable Long id, @RequestBody Compra compra) {
        return comprasService.actualizar(id, compra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        comprasService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
