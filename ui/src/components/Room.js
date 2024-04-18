import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

const Room = (props) => {

  const navigate = useNavigate();
  const { roomId } = useParams();

  // Using the room state variable to store the room data for display
  const [room, setRoom] = useState({
    roomNumber: "",
    roomType: "",
    status: "",
    maxOccupancy: "",
    amenities: "",
  });
  

  // Using the newRoom state variable to store the room data of the form, and update the room details
  const [newRoom, setNewRoom] = useState({
    roomNumber: room.roomNumber,
    roomType: room.roomType,
    status: room.status,
    maxOccupancy: room.maxOccupancy,
    amenities: room.amenities,
  });

  // toggleFormVisibility is responsible for the actions performed after the "Edit Room Details" button is clicked.
  const toggleFormVisibility = () => {
    const form = document.getElementById("roomForm");
    const roomInfoCard = document.getElementById("roomInfo");
    const editBtn = document.getElementById("edit-btn");

    editBtn.textContent = form.style.display === "none" ? "Cancel" : "Edit Room Details";
    form.style.display = form.style.display === "none" ? "block" : "none";
    roomInfoCard.style.display =
      form.style.display === "none" ? "block" : "none";
  };

  // Below useEffect hook is used to load the room details after checking the user is logged in.
  useEffect(() => {
    if (localStorage.getItem("user"))
      getRoom()
    else
      navigate("/")
  }, []);

  // Below useEffect hook is used to load the room details into the new room details form for editing purposes.
  useEffect(() => {
    if (room) {
      setNewRoom({
        roomNumber: room.roomNumber || "",
        roomType: room.roomType || "",
        status: room.status || "",
        maxOccupancy: room.maxOccupancy || "",
        amenities: JSON.stringify(room.amenities) || "",
      });
    }
  }, [room]);

  // This onChange helps in changing the newRoom details form
  const onChange = (e) => {
    setNewRoom({ ...newRoom, [e.target.name]: e.target.value });
  };

  // This function is invoked when the search button is called and pullsout the data of the room number
  const handleOnClick = async () => {
    const roomNumber1 = document.getElementById("room-number").value;
    try {
      const response = await fetch(
        `http://localhost:8080/room/getbyroom/${roomNumber1}`,
        {
          headers: {
            Authorization:
              "Basic " + JSON.parse(localStorage.getItem("user")).authdata,
          },
        }
      );
      const json = await response.json();
      setRoom(json);
    } catch (error) {
      props.showAlert("Something went wrong", "danger");
    }
  };

  // This function is invoked to change the room with the new details upon the form submission
  const handleEditRoom = async (e) => {
    const updatedRoom = {};
    if (room && newRoom) {
      for (const key in newRoom) {
        if (newRoom[key] !== room[key]) {
          updatedRoom[key] = newRoom[key];
        }
      }
      updatedRoom.amenities = JSON.parse(updatedRoom.amenities);
    }
    e.preventDefault();

    try {
      const response = await fetch(
        `http://localhost:8080/room/update/${roomId}`,
        {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json",
            Authorization:
              "Basic " + JSON.parse(localStorage.getItem("user")).authdata,
          },
          body: JSON.stringify(updatedRoom),
        }
      );
    if(response.ok){
      getRoom();
      props.showAlert("Successfully changed the room details","success");
      toggleFormVisibility();
    }else{
      props.showAlert("Something went wrong, make sure you are not providing duplicate room number!", "danger");
    }
    } catch (handleLogError) {
      props.showAlert("Something went wrong, please try again!", "danger");
    }
  };

  // This function is used to load the room details and set it to the room state variable, then display it on the page.
  const getRoom = async () => {
    try {
      const response = await fetch(`http://localhost:8080/room/get/${roomId}`, {
        headers: {
          Authorization:
            "Basic " + JSON.parse(localStorage.getItem("user")).authdata,
        },
      });
      const json = await response.json();
      setRoom(json);
    } catch (error) {
      props.showAlert(
        "You are not signed in ! Sign in First using login button",
        "danger"
      );
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center mt-3">
        <div className="col-md-2">
          <input
            type="text"
            id="room-number"
            className="form-control"
            placeholder="Enter room number"
          />
        </div>
        <div className="col-md-2">
          <button className="btn btn-primary mr-2" onClick={handleOnClick}>
            Submit
          </button>
        </div>
      </div>
      <button
        id="edit-btn"
        type="button"
        style={{ float: "right",margin:"10px" }}
        onClick={toggleFormVisibility}
        className="btn btn-primary float-right"
      >
        Edit Room Details
      </button>
      {room && (
        <div className="container mt-4">
          <h1>Room Details</h1>
          <div id="roomInfo" className="card">
            <div className="card-body">
              <h5 className="card-title">Room Number: {room.roomNumber}</h5>
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Room Type: {room.roomType}</li>
                <li className="list-group-item">Status: {room.status}</li>
                <li className="list-group-item">
                  Max Occupancy: {room.maxOccupancy}
                </li>
                <li className="list-group-item">
                  Amenities:
                  <ul className="list-group">
                    {Object.entries(room.amenities).map(([key, value]) => (
                      <li key={key} className="list-group-item">
                        {key}:{" "}
                        {typeof value === "object"
                          ? JSON.stringify(value)
                          : value}
                      </li>
                    ))}
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </div>
      )}
      <div id="roomForm" style={{display:"none"}} className="container">
        <div className="card ">
          <div className="card-body">
            <form onSubmit={handleEditRoom}>
              <h5 className="card-title">
                <label style={{ width: "215px" }} htmlFor="roomNumber">
                  Room Number:
                </label>
                <input
                  onChange={onChange}
                  type="text"
                  id="roomNumber"
                  name="roomNumber"
                  value={newRoom.roomNumber}
                />
              </h5>
              <br />
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <label style={{ width: "200px" }} htmlFor="roomType">
                    Room Type:
                  </label>
                  <input
                    onChange={onChange}
                    type="text"
                    id="roomType"
                    name="roomType"
                    value={newRoom.roomType}
                  />
                </li>
                <br />
                <li className="list-group-item">
                  <label style={{ width: "200px" }} htmlFor="status">
                    Status:
                  </label>
                  <select
                    onChange={onChange}
                    type="text"
                    id="status"
                    name="status"
                    value={newRoom.status}
                  >
                      <option value="dirty">Dirty</option>
                      <option value="clean">Clean</option>
                      <option value="ready">Ready</option>
                      <option value="out-of-service">Out-of-service</option>
                  </select>
                </li>
                <br />
                <li className="list-group-item">
                  <label style={{ width: "200px" }} htmlFor="maxOccupancy">
                    {" "}
                    Max Occupancy:
                  </label>
                  <input
                    onChange={onChange}
                    type="number"
                    id="maxOccupancy"
                    name="maxOccupancy"
                    value={newRoom.maxOccupancy}
                  />
                </li>
                <br />

                <li className="list-group-item">
                  <label style={{ width: "200px" }} htmlFor="amenities">
                    {" "}
                    Amenities:
                  </label>
                  <textarea
                    rows="1"
                    cols="100"
                    onChange={onChange}
                    id="amenities"
                    name="amenities"
                    value={newRoom.amenities}
                  />
                </li>
                <br />
              </ul>
              <button
                style={{ width: "120px" }}
                className="btn btn-primary"
                type="submit"
              >
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Room;
