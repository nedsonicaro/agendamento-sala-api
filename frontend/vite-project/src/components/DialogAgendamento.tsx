import {
  Autocomplete,
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField
} from '@mui/material'
import { DatePicker, LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import dayjs from 'dayjs'
import { useState } from 'react'
import { horarios, turnos } from '../constants/constants'
import { Agendamento } from '../models/Agendamento'
import { Sala } from '../models/Sala'

type DialogAgendamentoProps = {
  isOpen: boolean
  onClose: () => void
  agendamento: Agendamento
  salas: Sala[]
  onSubmit: (agendamento: Agendamento) => void
}

const DialogAgendamento = ({
  isOpen,
  onClose,
  agendamento,
  salas,
  onSubmit
}: DialogAgendamentoProps) => {
  const [sala, setSala] = useState(agendamento.sala)
  const [data, setData] = useState(dayjs(agendamento.data))
  const [agendamentoDescricao, setAgendamentoDescricao] = useState(
    agendamento.descricao
  )

  const [turno, setTurno] = useState(agendamento.turno)
  const [horario, setHorario] = useState(agendamento.horario)

  const onSave = () => {
    onSubmit({
      data: data.toDate(),
      descricao: agendamentoDescricao,
      horario,
      id: agendamento.id,
      turno,
      sala
    })
    onClose()
  }

  return (
    <Dialog open={isOpen} onClose={onClose}>
      <DialogTitle>{'Agendamento'}</DialogTitle>

      <DialogContent>
        <DialogContentText>
          <Box display={'flex'} gap={'1rem'} marginTop={'1rem'}>
            <TextField
              label={'Descrição'}
              value={agendamentoDescricao}
              onChange={e => setAgendamentoDescricao(e.target.value)}
            />
            <Autocomplete
              value={horario}
              options={horarios}
              onChange={(_e, value) => value && setHorario(value)}
              renderInput={params => <TextField {...params} label="Horário" />}
            />
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                label="Data"
                value={data}
                onChange={value => value && setData(value)}
              />
            </LocalizationProvider>
          </Box>
          <Box display={'flex'} gap={'1rem'} marginTop={'1rem'}>
            <Autocomplete
              fullWidth
              value={sala}
              getOptionLabel={(option: Sala) => option.descricao}
              options={salas}
              onChange={(_e, sala) => sala && setSala(sala)}
              renderInput={params => (
                <TextField {...params} label="Sala de Aula" />
              )}
            />
            <Autocomplete
              fullWidth
              value={turno}
              options={turnos}
              onChange={(_e, value) => value && setTurno(value)}
              renderInput={params => <TextField {...params} label="Turno" />}
            />
          </Box>
        </DialogContentText>
      </DialogContent>

      <DialogActions>
        <Button onClick={onClose}>Cancelar</Button>
        <Button onClick={onSave} autoFocus>
          Salvar
        </Button>
        <Button onClick={onSave} autoFocus>
          Deletar
        </Button>
      </DialogActions>
    </Dialog>
  )
}

export default DialogAgendamento
