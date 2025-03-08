package com.org.agendamento.domain.entity;

import com.org.agendamento.domain.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@Table(name = "sala")
@AllArgsConstructor
@NoArgsConstructor
public class Sala implements Serializable {

    @Id
    @Column(name = "id_sala", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String andar;

    @Column(nullable = false)
    private int capacidade;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
