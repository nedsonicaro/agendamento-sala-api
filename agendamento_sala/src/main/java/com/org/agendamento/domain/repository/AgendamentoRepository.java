package com.org.agendamento.domain.repository;

import com.org.agendamento.domain.entity.Agendamento;
import com.org.agendamento.domain.entity.Sala;
import com.org.agendamento.domain.entity.enums.Horario;
import com.org.agendamento.domain.entity.enums.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {

    boolean existsBySala(Sala sala);

    @Query
    ("""
    select
        count(agendamento) > 0
        from Agendamento agendamento
    where
        agendamento.sala = :sala
        and agendamento.data = :dataAgendamento
        and agendamento.horario = :horarioAgendamento
        and agendamento.turno = :turnoAgendamento
    """)
    boolean checarExistenciaDeAgendamentoDeSalaPorDataHorarioETurno(Sala sala,
                                                                    Date dataAgendamento,
                                                                    Horario horarioAgendamento,
                                                                    Turno turnoAgendamento);

    @Query("from Agendamento agendamento order by agendamento.data")
    List<Agendamento> obterTodosOrdenados();
}
