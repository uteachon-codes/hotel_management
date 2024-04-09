import AuthContext from "./AuthContext";
import React, {useState, useEffect } from 'react'

const  AuthState = (props)=>{
    const [user, setUser] = useState(null)
  
    useEffect(() => {
      const storedUser = JSON.parse(localStorage.getItem('user'))
      setUser(storedUser)
    }, [])
  
    const getUser = () => {
      return JSON.parse(localStorage.getItem('user'))
    }
  
    const userIsAuthenticated = () => {
      return localStorage.getItem('user') !== null
    }
  
    const userLogin = user => {
      localStorage.setItem('user', JSON.stringify(user))
      setUser(user)
    }
  
    const userLogout = () => {
      localStorage.removeItem('user')
      setUser(null)
    }
  
    const contextValue = {
      user,
      getUser,
      userIsAuthenticated,
      userLogin,
      userLogout,
    }
  
    return (
      <AuthContext.Provider value={contextValue}>
        {props.children}
      </AuthContext.Provider>
    )
  }

export default AuthState;