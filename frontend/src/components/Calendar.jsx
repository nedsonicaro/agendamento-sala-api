import React, { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import { getSchedules } from '../services/api.jsx';
import EventDetails from './EventDetail.jsx';

const localizer = momentLocalizer(moment);

const MyCalendar = () => {
    const [events, setEvents] = useState([]);
    const [selectedEvent, setSelectedEvent] = useState(null);

    useEffect(() => {
        const fetchSchedules = async () => {
            const response = await getSchedules();
            const formattedEvents = response.data.map(schedule => ({
                title: schedule.descricao,
                start: new Date(schedule.data),
                end: new Date(schedule.data),
                allDay: false,
                resource: schedule,
            }));
            setEvents(formattedEvents);
        };

        fetchSchedules();
    }, []);

    const handleSelectEvent = (event) => {
        setSelectedEvent(event.resource);
    };

    return (
        <div>
            <Calendar
                localizer={localizer}
                events={events}
                startAccessor="start"
                endAccessor="end"
                onSelectEvent={handleSelectEvent}
                style={{ height: 500 }}
            />
            {selectedEvent && <EventDetails event={selectedEvent} onClose={() => setSelectedEvent(null)} />}
        </div>
    );
};

export default MyCalendar;