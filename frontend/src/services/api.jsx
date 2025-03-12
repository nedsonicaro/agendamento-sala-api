import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080"
});

export const getRooms = () => api.get('/sala');
export const getSchedules = () => api.get('/agendamento');
export const createSchedule = (schedule) => api.post('/agendamento', schedule);
export const updateSchedule = (id, schedule) => api.put(`/agendamento/${id}`, schedule);
export const deleteSchedule = (id) => api.delete(`/agendamento/${id}`);

export default api;