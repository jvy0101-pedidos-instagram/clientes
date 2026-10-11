package cl.duocuc.clientes.service;

import cl.duocuc.clientes.exception.RecursoNoEncontradoException;
import cl.duocuc.clientes.exception.ReglaNegocioException;
import cl.duocuc.clientes.model.Cliente;
import cl.duocuc.clientes.repository.ClienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Capa de negocio: el controller llama al service y el service al repository. */
@Service
public class ClienteService {

    private final ClienteRepository repository;

    // Inyeccion de dependencias por constructor.
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe cliente con id " + id));
    }

    @Transactional
    public Cliente crear(Cliente nuevo) {
        if (repository.existsByUsuarioInstagram(nuevo.getUsuarioInstagram())) {
            throw new ReglaNegocioException("Ya existe un cliente con el usuario " + nuevo.getUsuarioInstagram());
        }
        nuevo.setId(null); // el id lo genera la base de datos
        return repository.save(nuevo);
    }

    @Transactional
    public Cliente actualizar(Long id, Cliente datos) {
        Cliente existente = buscarPorId(id);
        if (repository.existsByUsuarioInstagramAndIdNot(datos.getUsuarioInstagram(), id)) {
            throw new ReglaNegocioException("Ya existe un cliente con el usuario " + datos.getUsuarioInstagram());
        }
        existente.setNombre(datos.getNombre());
        existente.setUsuarioInstagram(datos.getUsuarioInstagram());
        existente.setTelefono(datos.getTelefono());
        existente.setEmail(datos.getEmail());
        existente.setDireccion(datos.getDireccion());
        return repository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Cliente existente = buscarPorId(id);
        repository.delete(existente);
    }
}
