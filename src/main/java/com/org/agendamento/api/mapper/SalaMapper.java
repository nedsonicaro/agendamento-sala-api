package com.org.agendamento.api.mapper;

import com.org.agendamento.api.dto.form.SalaForm;
import com.org.agendamento.api.dto.response.SalaResponse;
import com.org.agendamento.domain.entity.Sala;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SalaMapper {

    public static SalaResponse paraSalaResponse(Sala sala) {

        return isNull(sala) ? null :
                SalaResponse.builder()
                        .uuid(sala.getId())
                        .descricao(sala.getDescricao())
                        .andar(sala.getAndar())
                        .capacidade(sala.getCapacidade())
                        .status(sala.getStatus())
                        .build();
    }

    public static Sala paraSala(SalaForm form) {

        return Sala
                .builder()
                .descricao(form.descricao().trim())
                .andar(form.andar().trim())
                .capacidade(form.capacidade())
                .status(form.status())
                .build();
    }

    public static void atualizarSala(Sala sala,
                                     SalaForm form) {

        sala.setAndar(form.andar().trim());
        sala.setDescricao(form.descricao().trim());
        sala.setCapacidade(form.capacidade());
        sala.setStatus(form.status());
    }
}
