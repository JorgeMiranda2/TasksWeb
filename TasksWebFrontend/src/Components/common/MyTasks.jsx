import { Box, Button, Card, CardActions, CardContent, Modal, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import AddTaskModal from "./AddTaskModal";
import useTasks from "../Hooks/useTasks";
import useTags from "../Hooks/useTags";
import useTaskStates from "../Hooks/useTaskStates";
import "../../Css/Components/MyTasks.css";

const MyTasks = () => {

  const {getTasks, tasks, removeTask} = useTasks();
  const {tags, getTags} = useTags();
  const {taskStates, getTaskStates} = useTaskStates();

    const [openModal, setOpenModal] = useState(false);

//UseEffects

useEffect(()=>{
  getTags();
  getTaskStates();
  getTasks();
},[])
    

  const updateTasks = async  () => {
    console.log("doing updateTasks")
     await getTasks();
  };


    const handleOpenModal = () => {
        setOpenModal(true);
      };
      
      const handleCloseModal = () => {
        setOpenModal(false);
      };

    const convertDate = (date) => {
        if(date){
            return date.slice(0,10);
        }
   
    }
    
    return ( 
        <Box >
<Modal
  open={openModal}
  onClose={handleCloseModal}
  aria-labelledby="modal-modal-title"
  aria-describedby="modal-modal-description"
>
  <AddTaskModal 
  updateTasks={updateTasks}
  handleCloseModal={handleCloseModal}
  taskStates={taskStates}
  tags={tags} 
   />
</Modal>
        <h2  style = {{margin:"8px"}}>My tasks:</h2>
        <Box className="main">
        <ul className="tasks">
        {tasks.map((task, index) => (
          <li key={task.id}className="task" >
          <Card
           
  
          >
            <CardContent>
              <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                Task
              </Typography>

              <div className="task-options">
              <button className="edit" >Edit</button>
              <button  onClick={()=>removeTask(task.id)}  className="remove">Remove</button>
              </div>

              <Typography variant="h5" component="div">
                {task.title}
              </Typography>

              <Typography sx={{ mb: 1.5 }} color="text.secondary">
                {task.state.name}
              </Typography>

              <Typography variant="body2">
                Start Date: {convertDate(task.start)}
                <br />
                End Date: {convertDate(task.end)}
                <br />
                Description: {task.description}
              </Typography>

              <Typography>Tags:</Typography>
            
              <Box display="flex" flexWrap="wrap">
                {task.tags.map((tag, tagIndex) => (
                  <Typography key={tagIndex} variant="body2">
                {tag.name}
                  </Typography>
                ))}
              </Box>
            </CardContent>
          </Card>
          </li>
        ))}
      
      </ul>
      </Box>
      <Button  
        onClick={()=> handleOpenModal()}
      >add task</Button>
      <Typography variant="h2">Tags:</Typography>
    
      </Box>
     );
}
 
export default MyTasks;