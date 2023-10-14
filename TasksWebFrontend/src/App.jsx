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


function App() {
  useEffect(()=>{

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
