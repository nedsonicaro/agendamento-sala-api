import React from 'react';

const RoomList = ({ rooms, onSelectRoom }) => {
    return (
        <ul>
            {rooms.map(room => (
                <li key={room.id} onClick={() => onSelectRoom(room.id)} style={{ cursor: 'pointer', margin: '5px 0' }}>
                    {room.descricao}
                </li>
            ))}
        </ul>
    );
};

export default RoomList;