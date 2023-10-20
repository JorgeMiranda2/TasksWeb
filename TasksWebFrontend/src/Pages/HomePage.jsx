import * as React from "react";
import { Box, Button } from "@mui/material";
import MyTasks from "../Components/common/MyTasks";
import {useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import {setAuth} from "../redux/AuthSlice";

const HomePage = () => {
    const dispatch = useDispatch();
    const {auth} = useSelector(state => state.auth);
const AddTask = () => {
    pass
}
    return ( 
        <Box>
            <button onClick = {()=> {dispatch(setAuth({auth:true}))}} >oprimir</button>
            {console.log(auth)}
        <MyTasks/>
        </Box>
     );
}
 
export default HomePage;