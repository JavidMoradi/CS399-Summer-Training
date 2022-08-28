import React, { useEffect, useState } from "react";
import { Card } from "react-bootstrap";
import axios from "axios";
import "./PagesCard.css";
import { useNavigate } from "react-router-dom";

function PagesCard() {
  const [allPages, setAllPages] = useState([]);
  const navigation = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:8080/pages", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("accesstoken"),
        },
      })
      .then((res) => {
        setAllPages(res.data);
      })
      .catch((err) => {
        localStorage.getItem("accesstoken") == null ||
        err.response.status === 401
          ? navigation("/")
          : alert("error getting all pages!", err);
      });
  }, [JSON.stringify(allPages)]);

  return (
    <>
      <div
        className="card-div"
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Card style={{ width: "36rem" }}>
          <Card.Body>
            <Card.Title>Pages</Card.Title>
            <Card.Text>
              Please find all the pages and their corresponding methods below.
            </Card.Text>
            {allPages.map((element) => (
              <Card.Link key={element.id} href={"pages/" + element.id}>
                <br />
                <button type="button" className="btn btn-light">
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

export default PagesCard;
