import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import NavBar from './Components/Layouts/NavBar.jsx'
import { createRoot } from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import { routes } from './Config/Routes'
import PostAPI from './Services/PostAPI'
import { BACKEND_PATH } from './Config/Constants'
import {login,logout} from "./redux/AuthSlice";
import {useDispatch } from "react-redux";


function App() {

  const dispatch = useDispatch();




  const validateToken = () => {
    console.log("validating token")
    const userSession = window.localStorage.getItem("userSession");

    if(userSession && JSON.parse(userSession).token){
      const token =  JSON.parse(userSession).token;
      const username =  JSON.parse(userSession).userName;


      PostAPI(BACKEND_PATH + "/auth/validatetoken", {token:token}).then(
        (data) => {
          console.log(data,token);
          if(data.valid == true){
            dispatch(login({auth:true, username:username,token:token}));
          }else{

            dispatch(logout());
          }})    
        }


  }

  useEffect(()=>{
    validateToken();
  },[])
  
  const router = createBrowserRouter(routes);

  return (
    <>
  <NavBar/>
  <RouterProvider router={router} />
    </>
  )
}

export default App
