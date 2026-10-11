package cl.duocuc.clientes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El usuario de Instagram es obligatorio")
    @Column(nullable = false, unique = true, length = 60)
    private String usuarioInstagram;

    @Column(length = 20)
    private String telefono;

    @Email(message = "El email no es válido")
    @Column(length = 120)
    private String email;

    @Column(length = 200)
    private String direccion;
}
