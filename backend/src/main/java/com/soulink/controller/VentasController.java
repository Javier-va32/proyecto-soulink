package com.soulink.controller;

import com.soulink.model.Venta;
import com.soulink.service.VentasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentasController {

    private final VentasService ventasService;

    @GetMapping
    public List<Venta> listar() {
        return ventasService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Long id) {
        return ventasService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Venta crear(@RequestBody Venta venta) {
        return ventasService.guardar(venta);
    }

    @PutMapping("/{id}")
    public Venta actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        return ventasService.actualizar(id, venta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        ventasService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
