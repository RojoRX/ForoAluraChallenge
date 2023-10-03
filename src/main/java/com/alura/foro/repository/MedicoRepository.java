package com.alura.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.modelo.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
