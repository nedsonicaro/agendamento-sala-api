package com.org.agendamento.domain.entity;

import com.org.agendamento.domain.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "sala")
@AllArgsConstructor
@NoArgsConstructor
public class Sala implements Serializable {

    @Id
    @Column(name = "id_sala", nullable = false)
    private final UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String andar;

    @Column(nullable = false)
    private int capacidade;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return Objects.equals(id, sala.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
