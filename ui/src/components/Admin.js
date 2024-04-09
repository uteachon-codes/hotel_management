import React,{useState,useEffect} from 'react'
import {useNavigate} from 'react-router-dom';

const Admin = (props) => {

    const navigate = useNavigate();

  useEffect(() => {
    if ( !localStorage.getItem("user"))
      navigate("/")
  }, []);

    const [user, setUser] = useState({firstName:"",lastName:"",email:"",password:"",role:"USER"})


    const onChange = (e)=>{
        setUser({...user, [e.target.name]: e.target.value})
    }

    const handleSubmit = async (e) => {

        const postData = {
            firstName:user.firstName,
            lastName:user.lastName,
            email:user.email,
            password:user.password,
            role:user.role
            };
        e.preventDefault();
        try{
        const response = await fetch("http://localhost:8080/register", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization:
                "Basic " + JSON.parse(localStorage.getItem("user")).authdata,
              },
            body: JSON.stringify(postData)    
        });


        
        setUser({firstName:"",lastName:"",email:"",password:"",role:"USER"})

        }catch(handleLogError){
           props.showAlert("Something went wrong, please try again!","danger") 
        }
    }
    
  return (
    <div>
    <div className='container mt-3'>
            <form  onSubmit={handleSubmit}>
                <div className="mb-3">
                    <h3>Create User !!</h3>
                    <label htmlFor="firstName" className="form-label">First Name</label>
                    <input type="text" className="form-control" value={user.firstName} onChange={onChange} id="firstName" name="firstName" 
                    aria-describedby="firstNameHelp" />
                </div>
                <div className="mb-3">
                    <label htmlFor="lastName" className="form-label">Last Name</label>
                    <input type="text" className="form-control" value={user.lastName} onChange={onChange} id="lastName" name="lastName" aria-describedby="lastNameHelp" />
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email address</label>
                    <input type="email" className="form-control" value={user.email} onChange={onChange} id="email" name="email" aria-describedby="emailHelp" />
                </div>
                <div className="mb-3">
                    <label htmlFor="role" className="form-label">Role</label>
                    <select id="role" className='form-control' onChange={onChange} name="role" aria-describedby="roleHelp" value={user.role}>
                        <option value="USER">USER</option>
                        <option value="MANAGER">ADMIN</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input type="password" className="form-control" value={user.password} onChange={onChange} name="password" id="password" />
                </div>

                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>  
    </div>
  )
}

export default Admin
