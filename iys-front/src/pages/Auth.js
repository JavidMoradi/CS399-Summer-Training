import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { verify } from "../requests/Requests";
import "./Auth.css";

function Auth(props) {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });

  const nav = useNavigate();

  return (
    <div className="Auth-form-container">
      <form
        className="Auth-form"
        onSubmit={(event) => {
          event.preventDefault();

          verify(credentials, nav);
        }}
      >
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Log in</h3>
          <div className="form-group mt-3">
            <label>Username</label>
            <input
              id="username"
              type="Username"
              className="form-control mt-1"
              placeholder="Enter username"
              onChange={(event) => {
                setCredentials({
                  ...credentials,
                  username: event.target.value,
                });
              }}
            />
          </div>
          <div className="form-group mt-3">
            <label>Password</label>
            <input
              id="password"
              type="password"
              className="form-control mt-1"
              placeholder="Enter password"
              onChange={(event) => {
                setCredentials({
                  ...credentials,
                  password: event.target.value,
                });
              }}
            />
          </div>
          <br />
          <div className="d-grid gap-2 mt-3">
            <button type="submit" className="btn btn-primary">
              Submit
            </button>
          </div>
          <br />
        </div>
      </form>
    </div>
  );
}

export default Auth;
