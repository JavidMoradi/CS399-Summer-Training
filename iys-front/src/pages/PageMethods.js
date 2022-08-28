import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Card } from "react-bootstrap";
import "./PageMethods.css";
import { doCheckout } from "../requests/Requests";
import Navbar from "../components/Navbar";

function PageMethods() {
  const { id } = useParams(); // name of the useParam's variabel MUST match with the one specified at the Route path!
  const [thePage, setThePage] = useState([]);

  const navigation = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:8080/pages/" + id, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("accesstoken"),
        },
      })
      .then((res) => {
        setThePage(res.data.pageMethods);
      })
      .catch((err) => {
        localStorage.getItem("accesstoken") == null ||
        err.response.status === 401
          ? navigation("/")
          : alert("failed to get a single page!", err);
      });
  }, [JSON.stringify(thePage)]);

  return (
    <>
      <Navbar />
      <div
        className="card-method-div"
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Card style={{ width: "36rem" }}>
          <Card.Body>
            <Card.Title>Methods</Card.Title>
            <Card.Text>Please find all the methods of a page below.</Card.Text>
            {thePage.map((element) => (
              <Card.Link key={element.id}>
                <br />
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={() => doCheckout(element.id)} // The value for the onClick attribute should be a function, not a function call
                >
                  {element.name}
                </button>
                <br />
              </Card.Link>
            ))}
          </Card.Body>
        </Card>
      </div>
    </>
  );
}

export default PageMethods;
