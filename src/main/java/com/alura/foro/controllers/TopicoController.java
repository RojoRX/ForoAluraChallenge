package com.alura.foro.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.DatosListadoTopico;
import com.alura.foro.modelo.DatosRegistroTopico;
import com.alura.foro.modelo.DatosRespuestaTopico;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository autorRepository;
    @GetMapping
    public List<DatosListadoTopico> listadoTopicos(){
    	return topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList();
    }
    
    @PostMapping
    public ResponseEntity<String> cadastrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        try {
            // Verificar si el tópico ya existe (título y mensaje)
            if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un tópico con el mismo título y mensaje.");
            }

            // Recuperar el Autor y el Curso basándote en los IDs proporcionados
            Usuario autor = autorRepository.findById(datosRegistroTopico.autorId())
                    .orElseThrow(() -> {
                        throw new ResourceNotFoundException("Autor no encontrado con ID: " + datosRegistroTopico.autorId());
                    });

            Curso curso = cursoRepository.findById(datosRegistroTopico.cursoId())
                    .orElseThrow(() -> {
                        throw new ResourceNotFoundException("Curso no encontrado con ID: " + datosRegistroTopico.cursoId());
                    });
            // Crear el nuevo tópico
            Topico topico = new Topico(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje(), autor, curso);
            topicoRepository.save(topico);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tópico creado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud.");
        }
    }
}
