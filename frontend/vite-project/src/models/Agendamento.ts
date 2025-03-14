import { Sala } from './Sala'

export type Agendamento = {
  id: string
  sala: Sala
  data: Date
  descricao: string
  turno: string
  horario: string
}
