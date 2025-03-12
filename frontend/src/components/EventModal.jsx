import React, { useState } from 'react';

const EventModal = ({ date, events, onClose, onEdit, onDelete, onCreate }) => {
    const [newEventDescription, setNewEventDescription] = useState('');

    const handleCreate = () => {
        onCreate({ date, description: newEventDescription });
        setNewEventDescription('');
    };

    return (
        <div style={{ position: 'fixed', top: '50%', left: '50%', transform: 'translate(-50%, -50%)', padding: '20px', backgroundColor: 'white', border: '1px solid #ccc', zIndex: 1000 }}>
            <h2>Agendamentos para {date.toLocaleDateString()}</h2>
            <ul>
                {events.map(event => (
                    <li key={event.id}>
                        {event.descricao}
                        <button onClick={() => onEdit(event.id)}>Editar</button>
                        <button onClick={() => onDelete(event.id)}>Excluir</button>
                    </li>
                ))}
            </ul>
            <div>
                <input
                    type="text"
                    value={newEventDescription}
                    onChange={(e) => setNewEventDescription(e.target.value)}
                    placeholder="Nova descrição"
                />
                <button onClick={handleCreate}>Adicionar</button>
            </div>
            <button onClick={onClose}>Fechar</button>
        </div>
    );
};

export default EventModal;