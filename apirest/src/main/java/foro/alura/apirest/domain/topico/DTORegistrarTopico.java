package foro.alura.apirest.domain.topico;

import foro.alura.apirest.domain.curso.Curso;
import foro.alura.apirest.domain.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTORegistrarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull @Valid Usuario usuario,
        @NotNull @Valid Curso curso
) {
}
