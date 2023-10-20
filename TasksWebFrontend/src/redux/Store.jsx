import {configureStore} from "@reduxjs/toolkit";
import authReducer from "./AuthSlice";
//reducers



export default configureStore({
    reducer:{
        auth: authReducer,
    }
})