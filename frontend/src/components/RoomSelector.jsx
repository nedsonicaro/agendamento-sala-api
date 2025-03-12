import React, { useEffect, useState } from 'react';
import { getRooms } from '../services/api.jsx';

const RoomSelector = ({ onSelectRoom }) => {
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        const fetchRooms = async () => {
            const response = await getRooms();
            setRooms(response.data);
        };

        fetchRooms();
    }, []);

    return (
        <select onChange={(e) => onSelectRoom(e.target.value)}>
            <option value="">Selecione uma sala</option>
            {rooms.map(room => (
                <option key={room.id} value={room.id}>
                    {room.descricao} - {room.andar}
                </option>
            ))}
        </select>
    );
};

export default RoomSelector;