package foro.alura.apirest.domain.topico;

import foro.alura.apirest.domain.curso.Curso;
import foro.alura.apirest.domain.usuario.Usuario;

public record DTOActualizarTopico(Long id, String titulo, String mensaje, Usuario usuario, Curso curso) {
}
