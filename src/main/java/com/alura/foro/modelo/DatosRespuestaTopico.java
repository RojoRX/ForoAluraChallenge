package com.alura.foro.modelo;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
	    Long id,
	    String titulo,
	    String mensaje,
	    LocalDateTime fechaCreacion,
	    String statusTopico,
	    Long idAutor,
	    Long idCurso
	) {}
