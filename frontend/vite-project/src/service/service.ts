import axios, { AxiosResponse } from 'axios'
import { Agendamento } from '../models/Agendamento'
import { Sala } from '../models/Sala'

export type UpdateAgendamentoParam = {
  data: Date
  descricao: string
  turno: string
  horario: string
  sala: string
}

const api = axios.create({
  baseURL: 'http://localhost:8080'
})

export const getSalas = async () => {
  const response = api.get('/sala') as Promise<AxiosResponse>
  return (await response).data as Sala[]
}

export const getAgendamentos = async () => {
  const response = api.get('/agendamento') as Promise<AxiosResponse>
  return (await response).data as Agendamento[]
}

export const updateAgendamento = (
  id: string,
  agendamento: UpdateAgendamentoParam
) => api.put(`/agendamento/${id}`, agendamento) as Promise<void>

export const criaAgendamento = (agendamento: UpdateAgendamentoParam) =>
  api.post('/agendamento', agendamento) as Promise<void>

export const deleteAgendamento = (id: string) =>
  api.delete(`/agendamento/${id}`) as Promise<void>

export default api
