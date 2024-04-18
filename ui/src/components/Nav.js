import React,{useContext} from "react";
import { Link,useNavigate } from "react-router-dom";
import AuthContext from '../context/AuthContext'
const Nav = () => {

  const Auth = useContext(AuthContext);
  const navigate = useNavigate();
  const isLoggedIn = Auth.userIsAuthenticated();
  const isManager = isLoggedIn ? Auth.getUser().role === 'ROLE_MANAGER' : false;

  return <div>


<nav className="navbar navbar-expand-lg navbar-dark bg-primary">
  <div className="container-fluid">
    <Link className="navbar-brand" to="/">Crystal Inn & Suites <img style={{'width':'15px','paddingBottom':'5px'}} alt="logo" src={require('./img/logo.png')} /> </Link>
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon">Hi My naem</span>
    </button>
    <div className="collapse navbar-collapse" id="navbarNav">
      <ul className="navbar-nav ms-auto">
         { isManager &&
         <li className="nav-item">
          <Link className="nav-link active" aria-current="page" to="/admin">Admin Page</Link>
        </li>
        }

        { isLoggedIn && (
        <>
        <li className="nav-item">
          <Link className="nav-link active" aria-current="page" to="/rooms">Rooms</Link>
        </li>
        {/* <li className="nav-item">
          <Link className="nav-link" to="https://www.google.com">Google</Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="#">Pricing</Link>
        </li> */}

          </>
          )}
        { isLoggedIn ? (
          <li className="nav-item">
             <div  onClick={Auth.userLogout}>
             <Link className="nav-link active" to="/login">Logout</Link>
              </div>
          </li>
        ) :
        (<li className="nav-item"> 
            <button className="btn btn-primary "> 
              <Link className="nav-link" to="/login">Login</Link>
            </button>
        </li>)
      }
      </ul>

    </div>
  </div>
</nav>

  </div>;
};

export default Nav;
