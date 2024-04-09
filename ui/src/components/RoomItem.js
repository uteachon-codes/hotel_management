import React from 'react'
import { useNavigate } from 'react-router-dom'; 

const RoomItem = (props) => {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate(`/room/${room.id}`);
  };

    const room = props.room;
  return (
          <tr>
           
              <td> <button style={{ width: '100px' }} className='btn btn-primary' onClick={handleClick}>{room.roomNumber}   </button></td>
         
            <td>{room.roomType}</td>
            <td>{room.status}</td>
            <td>{room.maxOccupancy}</td>
            <td>{JSON.stringify(room.amenities)}</td>
          </tr>
  )
}

export default RoomItem
