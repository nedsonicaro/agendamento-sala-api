package com.org.agendamento.domain.repository;

import com.org.agendamento.domain.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SalaRepository extends JpaRepository<Sala, UUID> {

    @Query("from Sala sala where sala.status = 1")
    List<Sala> obterTodasAtivas();

    @Query
    ("""
    select
        count(sala) > 0
        from Sala sala
    where
        lower(sala.descricao) = lower(:descricao)
        and lower(sala.andar) = lower(:andar)
    """)
    boolean checarDuplicidadeDeSalaPorAndar(String descricao,
                                            String andar);
}
