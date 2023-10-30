import { useEffect, useState } from "react";
import GetAPI from "../../Services/GetAPI";
import { BACKEND_PATH } from "../../Config/Constants";

const useTaskStates = () => {
const [taskStates, setTaskStates] = useState([]);


    const createFormatTaskStates = (response) => {
    return response.map((taskState)=>{
        return {
            id:taskState.id,
            name:taskState.name,
            description:taskState.description
        }
    })
    }

    const getTaskStates = async () =>{
        try{
            const response = await GetAPI(BACKEND_PATH+"/api/taskstate");
            setTaskStates(createFormatTaskStates(response));

        }catch(e){
            console.log(e.error)
        }
    }
   
    return {getTaskStates, taskStates}
}
 
export default useTaskStates;