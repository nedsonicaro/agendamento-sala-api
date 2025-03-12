import React, { useState, useEffect } from 'react';
import { getRooms, getSchedules, createSchedule, updateSchedule, deleteSchedule } from './services/api';
import Sidebar from './components/Sidebar';
import MyCalendar from './components/Calendar';
import EventModal from './components/EventModal';
import './App.css';

const App = () => {
    const [rooms, setRooms] = useState([]);
    const [events, setEvents] = useState([]);
    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedRoom, setSelectedRoom] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            const roomsResponse = await getRooms();
            setRooms(roomsResponse.data);

            const schedulesResponse = await getSchedules();
            const formattedEvents = schedulesResponse.data.map(schedule => ({
                id: schedule.id,
                title: schedule.descricao,
                start: new Date(schedule.data),
                end: new Date(schedule.data),
                allDay: false,
                resource: schedule,
            }));
            setEvents(formattedEvents);
        };

        fetchData();
    }, []);

    const handleDaySelect = (date) => {
        setSelectedDate(date);
    };

    const handleCreateEvent = async (newEvent) => {
        const response = await createSchedule({
            sala_id: selectedRoom,
            data: newEvent.date,
            descricao: newEvent.description,
            turno: 'MANHA', // Defina o turno conforme necessário
            horario: 'A', // Defina o horário conforme necessário
        });
        setEvents([...events, {
            id: response.data.id,
            title: response.data.descricao,
            start: new Date(response.data.data),
            end: new Date(response.data.data),
            allDay: false,
            resource: response.data,
        }]);
    };

    const handleEditEvent = async (id, updatedEvent) => {
        await updateSchedule(id, updatedEvent);
        const updatedEvents = events.map(event =>
            event.id === id ? { ...event, title: updatedEvent.descricao } : event
        );
        setEvents(updatedEvents);
    };

    const handleDeleteEvent = async (id) => {
        await deleteSchedule(id);
        const updatedEvents = events.filter(event => event.id !== id);
        setEvents(updatedEvents);
    };

    return (
        <div style={{ display: 'flex' }}>
            <Sidebar rooms={rooms} onSelectRoom={setSelectedRoom} />
            <MyCalendar events={events} onDaySelect={handleDaySelect} />
            {selectedDate && (
                <EventModal
                    date={selectedDate}
                    events={events.filter(event => event.start.toDateString() === selectedDate.toDateString())}
                    onClose={() => setSelectedDate(null)}
                    onEdit={handleEditEvent}
                    onDelete={handleDeleteEvent}
                    onCreate={handleCreateEvent}
                />
            )}
        </div>
    );
};

export default App;