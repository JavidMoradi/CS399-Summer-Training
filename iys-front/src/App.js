import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Auth from "./pages/Auth";
import Pages from "./pages/Pages";
import PageMethods from "./pages/PageMethods";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Auth />}></Route>
          <Route path="/pages" element={<Pages />}></Route>
          <Route path="/pages/:id" element={<PageMethods />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
