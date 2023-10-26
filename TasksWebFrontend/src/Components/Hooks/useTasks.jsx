import { useState } from "react";
import { BACKEND_PATH } from "../../Config/Constants";
import GetAPI from "../../Services/GetAPI";

const useTasks = () => {

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
    const getTasks = async () => {
        try{
        const response = await GetAPI(BACKEND_PATH + "/api/mytasks")
        setTasks(createFormatTasks(response));
        }catch(e){
        console.log("error getting tasks: " + e)
        }
    }
    return {tasks , getTasks}
}
 
export default useTasks;