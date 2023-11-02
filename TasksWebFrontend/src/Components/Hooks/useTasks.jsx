import { useEffect, useState } from "react";
import { BACKEND_PATH } from "../../Config/Constants";
import GetAPI from "../../Services/GetAPI";
import PostAPI from "../../Services/PostAPI";
import DeleteAPI from "../../Services/DeleteAPI";
import { useCallback } from "react";
import PutAPI from "../../Services/PutAPI";

const useTasks = () => {

    const SPECIFIC_PATH = "/api/mytasks"
    const [tasks, setTasks] = useState([]);



    const createFormatTasks = (backendTasks) => {
        return backendTasks.map((task)=>{
            return {
                id: task.id,
                title:task.title,
                start:task.startDate.slice(0,16) , //converting to ISO 8601
                end:task.endDate.slice(0,16), //converting to ISO 8601
                description:task.description,
                state:task.taskState,
                tags:task.tags
            }
        })
    }

    const convertJsonToSend = (taskData) => {

        console.log(taskData)
        
            return {
                title:taskData.title,
                description:taskData.description,
                startDate:taskData.startDateTime,
                endDate:taskData.endDateTime,
                taskStateId:taskData.taskState,
                tagsId:taskData.tags
            }
        
    }


    const removeTask = async (id) => {
    try {
    const response = await DeleteAPI(BACKEND_PATH + SPECIFIC_PATH + `/${id}`);
    await getTasks();
    return response;
    } catch(error){
        console.log("error deleting task in the hook: " + error);
        return null;
    }
    }

    const getTasks = async () => {
        try{
        const response = await GetAPI(BACKEND_PATH + SPECIFIC_PATH)
        setTasks(createFormatTasks(response));
        }catch(e){
        console.log("error getting tasks: " + e)
        }
    }

    const updateTask = useCallback(async (id, params) =>{
        try {
        const response = await PutAPI(BACKEND_PATH + SPECIFIC_PATH + `/${id}`, convertJsonToSend(params));
     
        return response;
        }catch(error){
            console.log("Error in Hook task updating data");
            return error;
        }
    },[]) 

    const sendTask = useCallback(async (taskData) => {
 
        try {
        await PostAPI(BACKEND_PATH + SPECIFIC_PATH, convertJsonToSend(taskData));
      
        } catch (e) {
            console.log("Error sending task: " + e);
        }
    },[]);


    return {tasks , getTasks, sendTask, removeTask, updateTask}

    
}
 
export default useTasks;