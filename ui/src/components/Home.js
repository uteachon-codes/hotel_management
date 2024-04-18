import React, { useContext, useEffect } from 'react'
import Rooms from './Rooms'
import AuthContext from '../context/AuthContext'
import { useNavigate } from 'react-router-dom'


const Home = (props) => {
    const navigate = useNavigate();
    const Auth = useContext(AuthContext)
    const isLoggedIn = Auth.userIsAuthenticated()
    useEffect(() => {
        if( !isLoggedIn ){
            navigate("/login")
        }
    }, [])
    

  return (
    <div>
        <Rooms showAlert={props.showAlert} />
    </div>
  )
}

export default Home
