package foro.alura.apirest.service;

import foro.alura.apirest.domain.topico.Topico;
import foro.alura.apirest.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public Topico crearTopico(Topico topico) {
        return topicoRepository.save(topico);
    }

    public Page<Topico> obtenerTopicos(Pageable pageable) {
        return topicoRepository.findAll(pageable);
    }

    public Optional<Topico> obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico actualizarTopico(Long id, Topico topico) {
        if (topicoRepository.existsById(id)) {
            topico.setId(id);
            return topicoRepository.save(topico);
        }
        return null;
    }

    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}