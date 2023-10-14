import { Link } from "react-router-dom";
import LoginPage from "../Pages/LoginPage";
import RegisterPage from "../Pages/RegisterPage";

export const routes = [
    {
      path: "/",
      element: (
        <div>
          <h1>Hello World</h1>
          <Link to="about">About Us</Link>
        </div>
      ),
    },
    {
      path: "login",
      element: <LoginPage/>,
    },
    {
        path: "register",
        element: <RegisterPage/>,
      },
  ];