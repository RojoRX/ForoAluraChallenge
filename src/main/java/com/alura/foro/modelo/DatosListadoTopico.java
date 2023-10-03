package com.alura.foro.modelo;

import java.time.LocalDateTime;

public record DatosListadoTopico(
	    String titulo,
	    String mensaje,
	    LocalDateTime fechaCreacion,
	    String status,
	    Long autorId,
	    Long cursoId
	) {

	    public DatosListadoTopico(Topico topico) {
	        this(
	            topico.getTitulo(),
	            topico.getMensaje(),
	            topico.getFechaCreacion(),
	            topico.getStatusTopico().toString(),
	            topico.getAutor().getId(),
	            topico.getCurso().getId()
	        );
	    }
	}
