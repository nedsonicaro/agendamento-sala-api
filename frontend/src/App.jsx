import React, { useState } from 'react';
import MyCalendar from './components/Calendar.jsx';
import RoomSelector from './components/RoomSelector.jsx';

const App = () => {
    const [selectedRoom, setSelectedRoom] = useState(null);

    return (
        <div>
            <h1>Agendamento de Salas</h1>
            <RoomSelector onSelectRoom={setSelectedRoom} />
            <MyCalendar selectedRoom={selectedRoom} />
        </div>
    );
};

export default App;