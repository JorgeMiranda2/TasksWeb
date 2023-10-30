import { useEffect, useState } from "react";
import { BACKEND_PATH } from "../../Config/Constants";
import GetAPI from "../../Services/GetAPI";
import PostAPI from "../../Services/PostAPI";

const useTasks = () => {

    const SPECIFIC_PATH = "/api/mytasks"
    const [tasks, setTasks] = useState([]);



    const createFormatTasks = (backendTasks) => {
        return backendTasks.map((task)=>{
            return {
                id: crypto.randomUUID(),
                title:task.title,
                start:task.startDate,
                end:task.endDate,
                description:task.description,
                state:task.taskStateName,
                tags:task.tags
            }
        })
    }

    const convertJsonToSend = (taskData) => {
        
            return {
                title:taskData.title,
                description:taskData.description,
                startDate:taskData.startDateTime,
                endDate:taskData.endDateTime,
                taskStateName:taskData.taskState,
                tags:taskData.tags
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

    const sendTask = async (taskData) => {
        try {
        await PostAPI(BACKEND_PATH + SPECIFIC_PATH, convertJsonToSend(taskData))
        } catch (e) {
            console.log("Error sending task: " + e);
        }
    };


    return {tasks , getTasks, sendTask}

    
}
 
export default useTasks;