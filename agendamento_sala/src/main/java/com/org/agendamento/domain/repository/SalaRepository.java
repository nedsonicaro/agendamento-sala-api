package com.org.agendamento.domain.repository;

import com.org.agendamento.domain.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalaRepository extends JpaRepository<Sala, UUID> {


}
