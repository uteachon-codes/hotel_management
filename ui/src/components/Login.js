import React, {useContext, useState} from 'react'
import { Navigate, useNavigate } from 'react-router-dom'
import AuthContext from '../context/AuthContext'
import { handleLogError } from './Helpers'

const Login = (props) => {

    const Auth = useContext(AuthContext);
    const navigate = useNavigate();
    const isLoggedIn = Auth.userIsAuthenticated();
    
    if(isLoggedIn){
       navigate("/")
    }

    const [credentials, setCredentials] = useState({email: "", password: ""}) 

    const handleSubmit = async (e) => {

        const postData = {
            email: credentials.email,
            password: credentials.password
          };
        e.preventDefault();

        try{
        const response = await fetch("http://localhost:8080/authenticate", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
              },
            body: JSON.stringify(postData)    
        });
         
        const json = await response.json()
        const {id,email,role} = json

        const authdata = window.btoa(credentials.email+':'+credentials.password);
        const authenticatedUser = { id, email, role, authdata }
        Auth.userLogin(authenticatedUser)
        navigate("/");
        }catch(handleLogError){
           props.showAlert("Something went wrong, please try again with valid credentials !","danger") 
        }
    }

    const onChange = (e)=>{
        setCredentials({...credentials, [e.target.name]: e.target.value})
    }

    return (
        <div className='container mt-3'>
            <form  onSubmit={handleSubmit}>
                <div className="mb-3">
                    <h3>Login Here !!</h3>
                    <label htmlFor="email" className="form-label">Email address</label>
                    <input type="email" className="form-control" value={credentials.email} onChange={onChange} id="email" name="email" aria-describedby="emailHelp" />
               
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input type="password" className="form-control" value={credentials.password} onChange={onChange} name="password" id="password" />
                </div>

                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>
    )
}

export default Login