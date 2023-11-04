import * as React from "react";
import { Box, Button, Typography } from "@mui/material";
import MyTasks from "../Components/common/MyTasks";
import {useSelector, useDispatch } from "react-redux";


const HomePage = () => {

    const {auth} = useSelector(state => state.auth);
const AddTask = () => {
    prompt("xd")
}
    return ( 
        <Box>
         {auth ?  <MyTasks/> : 
         <Box style={{display:"flex" , flexDirection:"column" , justifyContent:"center", alignItems:"center", marginTop:"30px"}}>
            <Typography variant="h5">Login or register to create and see your tasks!</Typography>
         </Box>
         }
 
        </Box>
     );
}
 
export default HomePage;