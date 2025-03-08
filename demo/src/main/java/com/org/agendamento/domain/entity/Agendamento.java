package com.org.agendamento.domain.entity;

import com.org.agendamento.domain.entity.enums.Horario;
import com.org.agendamento.domain.entity.enums.Turno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Builder
@Table(name = "agendamento")
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento implements Serializable {

    @Id
    @Column(name = "id_agendamento", nullable = false)
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "id_sala", nullable = false)
    private Sala sala;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Turno turno;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Horario horario;
}
