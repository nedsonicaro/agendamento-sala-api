import React from 'react';

const EventDetails = ({ event, onClose }) => {
    return (
        <div style={{ marginTop: '20px', padding: '10px', border: '1px solid #ccc' }}>
            <h3>{event.descricao}</h3>
            <p>Data: {new Date(event.data).toLocaleDateString()}</p>
            <p>Turno: {event.turno}</p>
            <p>Horário: {event.horario}</p>
            <button onClick={onClose}>Fechar</button>
        </div>
    );
};

export default EventDetails;