import React, { useState,useEffect } from 'react';
import RoomItem from './RoomItem';

import {useNavigate} from 'react-router-dom';

const Rooms = (props) => {

const [rooms, setRooms] = useState([]);
const navigate = useNavigate();

useEffect(() => {
    if(localStorage.getItem('user'))
      getRooms();
    else
      navigate("/");
}, [])

const getRooms = async () => {
    try{
    const response = await fetch(`http://localhost:8080/room/get`,
        {headers: {'Authorization': 'Basic '+JSON.parse(localStorage.getItem('user')).authdata}});
    const json = await response.json();
    setRooms(json);
  }catch(error){
    console.log("Something went wrong !!");
    props.showAlert("You are not signed in ! Sign in First using login button","danger");
  }
}

  return (
    <>
    <div className='container justify-content-center' >
   

        <h2 className="text-center text-white bg-primary primary my-3"> Room Information !!</h2>
         <table className="table table-striped">
            
            <thead>
          <tr>
            <th>Room #</th>
            <th>Room Type</th>
            <th>Status</th>
            <th>Max Occupancy</th>
            <th>Amenities</th>
          </tr>
        </thead>
        <tbody>
            { rooms.length > 1 && rooms.map((room) => {
            return <RoomItem key={room.id} room={room}/>
          })}
        </tbody>
    </table>
    </div>
    </>
  )
}
export default Rooms
