import { useNavigate } from 'react-router-dom';
import LoginPage from "../Pages/LoginPage";
import RegisterPage from "../Pages/RegisterPage";
import HomePage from "../Pages/HomePage";
import {useSelector} from "react-redux";


function RequireAuth({component:Component, ...rest}) {
  const navigate = useNavigate();
  const {auth} = useSelector(state => state.auth);

    if (auth) {
      // Si el usuario está autenticado, redirige a otra página
      navigate('/');
      return null;
    } else {
      // Si el usuario no está autenticado, muestra el componente proporcionado
      return <Component {...rest} />;
    }
  };


export const routes = [
    {
      path: "/",
      element: <HomePage/>,
    },
    {
      path: "login",
      element: <RequireAuth component={LoginPage}/>,
    },
    {
        path: "register",
        element: <RequireAuth component={RegisterPage}/>,
      },
  ];