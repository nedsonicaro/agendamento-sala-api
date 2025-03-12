import React from 'react';
import RoomList from './RoomList';

const Sidebar = ({ rooms, onSelectRoom }) => {
    const roomsByFloor = rooms.reduce((acc, room) => {
        if (!acc[room.andar]) {
            acc[room.andar] = [];
        }
        acc[room.andar].push(room);
        return acc;
    }, {});

    return (
        <div style={{ width: '250px', padding: '10px', borderRight: '1px solid #ccc' }}>
            {Object.entries(roomsByFloor).map(([floor, rooms]) => (
                <div key={floor}>
                    <h3>Andar {floor}</h3>
                    <RoomList rooms={rooms} onSelectRoom={onSelectRoom} />
                </div>
            ))}
        </div>
    );
};

export default Sidebar;