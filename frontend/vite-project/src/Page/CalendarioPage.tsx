import moment from 'moment'
import 'moment/dist/locale/pt-br'
import React, { useEffect, useState } from 'react'
import { Calendar, EventProps, momentLocalizer, View } from 'react-big-calendar'
import 'react-big-calendar/lib/css/react-big-calendar.css'
import DialogAgendamento from '../components/DialogAgendamento'
import { toUpdateAgendamentoParam } from '../mappers/agendamento'
import { Agendamento } from '../models/Agendamento'
import { Sala } from '../models/Sala'
import {
  getAgendamentos,
  getSalas,
  updateAgendamento
} from '../service/service'

moment.locale('pt-br')
const localizer = momentLocalizer(moment)

const messages = {
  allDay: 'Dia inteiro',
  previous: 'Anterior',
  next: 'Próximo',
  today: 'Hoje',
  month: 'Mês',
  week: 'Semana',
  day: 'Dia',
  agenda: 'Agenda',
  date: 'Data',
  time: 'Hora',
  event: 'Evento',
  noEventsInRange: 'Nenhum evento neste período.',
  showMore: (total: number) => `+ Ver mais (${total})`
}

type EventComponent = {
  title: string
  start: Date
  end: Date

  sala: string
  turno: string
  id: string
}

const CalendarioPage: React.FC = () => {
  const getDataDefaultByAgendamentos = (agendamentos: Agendamento[]) => {
    return agendamentos.length > 0 ? agendamentos[0].data : new Date()
  }

  const [selectedSala, setSelectedSala] = useState<string>('')
  const [agendamentos, setAgendamentos] = useState<Agendamento[]>([])
  const [view, setView] = useState<View>('month')
  const [currentDate, setCurrentDate] = useState<Date>(new Date())
  const [dialogIsOpen, setDialogIsOpen] = useState(false)
  const [agendamentoSelecionado, setAgendamentoSelecionado] =
    useState<Agendamento>()

  const [salas, setSalas] = useState<Sala[]>()

  const handleSucces = (response: Agendamento[]) => {
    setCurrentDate(getDataDefaultByAgendamentos(response))
    setAgendamentos(response)
  }

  useEffect(() => {
    getAgendamentos()
      .then(handleSucces)
      .catch(e => console.error('Erro ao buscar agendamentos:', e))

    getSalas()
      .then((r: Sala[]) => setSalas(r))
      .catch(e => console.log(e))
  }, [])

  const salasUnicas = Array.from(
    new Set(agendamentos.map(a => a.sala.descricao))
  )

  const filteredEvents = agendamentos
    .filter(event => !selectedSala || event.sala.descricao === selectedSala)
    .map(event => ({
      title: `${event.descricao} - ${event.sala.descricao}`,
      start: moment(event.data).toDate(),
      end: moment(event.data).add(1, 'hour').toDate(),
      sala: event.sala.descricao,
      turno: event.turno,
      id: event.id
    }))

  const handleNavigate = (newDate: Date) => {
    setCurrentDate(newDate)
  }

  const handleViewChange = (newView: View) => {
    setView(newView)
  }

  const EventComponent: React.FC<EventProps<EventComponent>> = ({ event }) => (
    <div
      style={{
        padding: '5px',
        backgroundColor: '#3498db',
        color: 'white',
        borderRadius: '4px',
        fontSize: '12px'
      }}
      onClick={() => handleClick(event.id)}
      title={`${event.title}`}
    >
      <strong>{event.title}</strong>
    </div>
  )

  const onClose = () => {
    setDialogIsOpen(false)
  }

  const findAgendamentoById = (id: string) => {
    return agendamentos.find(agd => agd.id === id)
  }

  const handleClick = (idAgendamento: string) => {
    const agendamento = findAgendamentoById(idAgendamento)

    if (agendamento) {
      setAgendamentoSelecionado(agendamento)
      setDialogIsOpen(true)
    }
  }

  const onSubmitAgendamento = (agendamento: Agendamento) => {
    updateAgendamento(agendamento.id, toUpdateAgendamentoParam(agendamento))
  }

  return (
    <>
      <div
        style={{
          display: 'flex',
          height: '100vh',
          fontFamily: 'Roboto, sans-serif'
        }}
      >
        {/* Sidebar de Filtros */}
        <div
          style={{
            width: '250px',
            padding: '20px',
            background: '#f4f4f4',
            borderRight: '1px solid #ddd'
          }}
        >
          <h3>Filtros</h3>
          <label>Selecione a sala</label>
          <select
            value={selectedSala}
            onChange={e => setSelectedSala(e.target.value)}
            style={{ width: '100%', padding: '5px', marginTop: '10px' }}
          >
            <option value="">Todas as Salas</option>
            {salasUnicas.map(sala => (
              <option key={sala} value={sala}>
                {sala}
              </option>
            ))}
          </select>
        </div>

        {/* Calendário */}
        <div style={{ flex: 1, padding: '20px' }}>
          <Calendar
            key={currentDate.toString()}
            localizer={localizer}
            events={filteredEvents}
            startAccessor="start"
            endAccessor="end"
            messages={messages}
            components={{ event: EventComponent }}
            date={currentDate}
            onNavigate={handleNavigate}
            view={view}
            onView={handleViewChange}
            style={{ fontFamily: 'Roboto, sans-serif' }}
          />
        </div>
      </div>
      {agendamentoSelecionado && salas && (
        <DialogAgendamento
          isOpen={dialogIsOpen}
          onClose={onClose}
          agendamento={agendamentoSelecionado}
          salas={salas}
          onSubmit={onSubmitAgendamento}
        />
      )}
    </>
  )
}

export default CalendarioPage
