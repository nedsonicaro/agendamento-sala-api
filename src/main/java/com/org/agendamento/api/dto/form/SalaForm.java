package com.org.agendamento.api.dto.form;

import com.org.agendamento.domain.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SalaForm(

        @NotBlank(message = "A descrição é obrigatória")
        @Size(min = 3, max = 50, message = "A descrição deve possuir de 3 a 50 caracteres.")
        String descricao,

        @NotBlank(message = "O andar é obrigatória")
        String andar,

        @NotNull(message = "A capacidade é obrigatória")
        int capacidade,

        @NotNull(message = "O status é obrigatório")
        Status status
) {
}
