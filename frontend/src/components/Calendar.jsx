import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';

const localizer = momentLocalizer(moment);

const MyCalendar = ({ events, onDaySelect }) => {
    return (
        <div style={{ flex: 1, padding: '10px' }}>
            <Calendar
                localizer={localizer}
                events={events}
                startAccessor="start"
                endAccessor="end"
                onSelectSlot={(slotInfo) => onDaySelect(slotInfo.start)}
                selectable
                style={{ height: 500 }}
            />
        </div>
    );
};

export default MyCalendar;