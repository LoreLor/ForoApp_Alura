package foro.alura.apirest.domain.topico;

import foro.alura.apirest.domain.curso.Curso;
import foro.alura.apirest.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DTORespuestaTopico(Long id,
                                 String titulo,
                                 String mensaje,
                                 LocalDateTime fechaCreacion,
                                 StatusTopico status,
                                 Usuario usuario,
                                 Curso curso) {
}
