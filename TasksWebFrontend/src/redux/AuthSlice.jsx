import {createSlice} from "@reduxjs/toolkit";



const initialState = {
    auth:false,
    username:"",
    token:""
}

export const AuthSlice = createSlice({
    name:"auth",
    initialState,
    reducers:{
        login:(state, action) => {
            state.auth = action.payload.auth;
            state.username = action.payload.username;
            state.token = action.payload.token;
           
        },
        logout:(state) => {
            state.auth = false;
            state.username = "";
            state.token = "";
            window.localStorage.removeItem("userSession");
        }
    }
})

export const {login, logout} = AuthSlice.actions;
export default AuthSlice.reducer;