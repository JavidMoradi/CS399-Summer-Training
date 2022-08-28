import { useNavigate } from "react-router-dom";
import { logout } from "../requests/Requests";

function Navbar(props) {
  const nav = useNavigate();

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <button
                className="btn btn-outline-danger my-2 my-sm-0 m-2"
                onClick={() => logout(nav)}
              >
                Log out
              </button>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
}

export default Navbar;
