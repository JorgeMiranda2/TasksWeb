import {createSlice} from "@reduxjs/toolkit";



const initialState = {
    auth:false
}

export const AuthSlice = createSlice({
    name:"auth",
    initialState,
    reducers:{
        setAuth:(state, action) => {
            state.auth = action.payload.auth;
           
        }
    }
})

export const {setAuth} = AuthSlice.actions;
export default AuthSlice.reducer;