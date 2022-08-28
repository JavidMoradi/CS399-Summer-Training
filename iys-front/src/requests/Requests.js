import axios from "axios";
import { useNavigate } from "react-router-dom";

export const verify = async ({ username, password }, navigation) => {
  if (username == "" || password == "") {
    return alert("Fields cannot be left empty!");
  }

  await axios
    .post("http://localhost:8080/api/login", {
      username: username,
      password: password,
    })
    .then((res) => {
      // save response's access token to local storage
      localStorage.setItem("accesstoken", res.data.accessToken);

      navigation("/pages");
    })
    .catch((err) => {
      alert("Given credentials are not valid!", err);

      navigation("/");
    });
};

export const doCheckout = async (methodId) => {
  await axios
    .get("http://localhost:8080/methods/" + methodId, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("accesstoken"),
      },
    })
    .then((res) => {
      alert("you have the permission for this method!");
    })
    .catch((err) => {
      if (err.response.status == 403) {
        alert("you don't have the permission for this method!");
      } else {
        alert("failed to access the method.");
      }
    });
};

export const logout = (navigation) => {
  navigation("/");
  localStorage.clear();
};
