package foro.alura.apirest.repository;

import foro.alura.apirest.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
