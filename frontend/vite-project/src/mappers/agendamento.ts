import { Agendamento } from '../models/Agendamento'
import { UpdateAgendamentoParam } from '../service/service'

export const toUpdateAgendamentoParam = (
  agendamento: Agendamento
): UpdateAgendamentoParam => {
  return {
    ...agendamento,
    sala: agendamento.sala.uuid
  }
}
