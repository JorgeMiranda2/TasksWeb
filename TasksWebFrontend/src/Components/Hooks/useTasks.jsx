import { useEffect, useState } from "react";
import { BACKEND_PATH } from "../../Config/Constants";
import GetAPI from "../../Services/GetAPI";
import PostAPI from "../../Services/PostAPI";
import DeleteAPI from "../../Services/DeleteAPI";

const useTasks = () => {

    const SPECIFIC_PATH = "/api/mytasks"
    const [tasks, setTasks] = useState([]);



    const createFormatTasks = (backendTasks) => {
        return backendTasks.map((task)=>{
            return {
                id: task.id,
                title:task.title,
                start:task.startDate,
                end:task.endDate,
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

    const sendTask = async (taskData) => {
        try {
        await PostAPI(BACKEND_PATH + SPECIFIC_PATH, convertJsonToSend(taskData))
        } catch (e) {
            console.log("Error sending task: " + e);
        }
    };


    return {tasks , getTasks, sendTask, removeTask}

    
}
 
export default useTasks;