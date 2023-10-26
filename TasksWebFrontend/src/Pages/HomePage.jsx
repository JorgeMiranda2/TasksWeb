import * as React from "react";
import { Box, Button } from "@mui/material";
import MyTasks from "../Components/common/MyTasks";
import {useSelector, useDispatch } from "react-redux";
import {login,logout} from "../redux/AuthSlice";

const HomePage = () => {
    const dispatch = useDispatch();
    const {auth} = useSelector(state => state.auth);
const AddTask = () => {
    prompt("xd")
}
    return ( 
        <Box>
         {auth &&  <MyTasks/> }
 
        </Box>
     );
}
 
export default HomePage;