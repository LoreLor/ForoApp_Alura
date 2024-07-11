package foro.alura.apirest.controller;

import foro.alura.apirest.domain.curso.Curso;
import foro.alura.apirest.domain.topico.*;
import foro.alura.apirest.domain.usuario.Usuario;
import foro.alura.apirest.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DTORespuestaTopico> registrarTopico(@RequestBody @Valid DTORegistrarTopico registro, UriComponentsBuilder uri) {
        System.out.println("El request llega correctamente!");
        System.out.println(registro);
        Topico topico = topicoRepository.save(new Topico(registro));
        DTORespuestaTopico datosDeRespuestaTopico = new DTORespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new Usuario(
                        topico.getUsuario().getId(),
                        topico.getUsuario().getNombre(),
                        topico.getUsuario().getEmail(),
                        topico.getUsuario().getContrasena()
                ),
                new Curso(
                        topico.getCurso().getId(),
                        topico.getCurso().getNombre(),
                        topico.getCurso().getCategoria()
                )
        );
        URI url = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosDeRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DTOListarTopico>> listadoMedicos(Pageable paginacion) {
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DTOListarTopico::new));
    }

    // GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<DTORespuestaTopico> listarTopicosPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DTORespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new Usuario(
                        topico.getUsuario().getId(),
                        topico.getUsuario().getNombre(),
                        topico.getUsuario().getEmail(),
                        topico.getUsuario().getContrasena()
                ),
                new Curso(
                        topico.getCurso().getId(),
                        topico.getCurso().getNombre(),
                        topico.getCurso().getCategoria()
                )
        );
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTORespuestaTopico> actualizarTopico(@RequestBody @Valid DTOActualizarTopico datosActualizar) {
        Topico topico = topicoRepository.getReferenceById(datosActualizar.id());
        topico.actualizarDatos(datosActualizar);
        return ResponseEntity.ok( new DTORespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new Usuario(
                        topico.getUsuario().getId(),
                        topico.getUsuario().getNombre(),
                        topico.getUsuario().getEmail(),
                        topico.getUsuario().getContrasena()
                ),
                new Curso(
                        topico.getCurso().getId(),
                        topico.getCurso().getNombre(),
                        topico.getCurso().getCategoria()
                )
        ));
    }

    // **** DELETE ****
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        //topico.desactivarTopico();  // Delete Lógico (No en DB)
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}