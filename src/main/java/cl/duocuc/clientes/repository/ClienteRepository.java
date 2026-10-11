package cl.duocuc.clientes.repository;

import cl.duocuc.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByUsuarioInstagram(String usuarioInstagram);

    boolean existsByUsuarioInstagramAndIdNot(String usuarioInstagram, Long id);
}
