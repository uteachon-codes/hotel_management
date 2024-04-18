import "./App.css";
import Nav from "./components/Nav";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Rooms from "./components/Rooms";
import AuthState from "./context/AuthState";

import { useEffect, useState } from "react";
import Login from "./components/Login";
import Alert from "./components/Alert";
import Home from "./components/Home";
import Room from "./components/Room";
import Admin from "./components/Admin";


function App() {

  const [alert, setAlert] = useState(null);
  const showAlert = (message, type)=>{
    setAlert({
      msg: message,
      type: type
    })
    setTimeout(() => {
        setAlert(null);
    }, 1500);
}  
  return (
    <>
     <AuthState>
      <Router>
        <Nav />
        <Alert alert={alert} />
        <Routes>
          <Route exact path="/" element={<Home showAlert={showAlert}/>} />
          <Route exact path="/login" element={<Login showAlert={showAlert}/>} />
          <Route exact path="/rooms" element={<Rooms showAlert={showAlert}/>} />
          <Route exact path="/room/:roomId" element={<Room showAlert={showAlert}/>} />
          <Route exact path="/admin" element={<Admin showAlert={showAlert}/>} />
        </Routes>
      </Router>
      </AuthState>
    </>
  );
}

export default App;
