package cl.duocuc.clientes.controller;

import cl.duocuc.clientes.model.Cliente;
import cl.duocuc.clientes.service.ClienteService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Capa de presentacion: expone los endpoints REST del microservicio. */
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // GET /api/v1/clientes
    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    // GET /api/v1/clientes/{id}
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // POST /api/v1/clientes  -> 201 Created
    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        Cliente creado = service.crear(cliente);
        return ResponseEntity.created(URI.create("/api/v1/clientes/" + creado.getId())).body(creado);
    }

    // PUT /api/v1/clientes/{id}
    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return service.actualizar(id, cliente);
    }

    // DELETE /api/v1/clientes/{id}  -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
