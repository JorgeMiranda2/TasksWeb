import { Box, Button, Card, CardActions, CardContent, Modal, Typography } from "@mui/material";
import { useEffect, useRef, useState } from "react";
import { useSearchParams } from "react-router-dom";
import AddTaskModal from "./AddTaskModal";
import useTasks from "../Hooks/useTasks";
import useTags from "../Hooks/useTags";
import useTaskStates from "../Hooks/useTaskStates";
import "../../Css/Components/MyTasks.css";
import AddIcon from '@mui/icons-material/Add';

const MyTasks = () => {

  const {getTasks, tasks, removeTask} = useTasks();
  const {tags, getTags} = useTags();
  const {taskStates, getTaskStates} = useTaskStates();
  const [openModal, setOpenModal] = useState(false);

  const [modalData,setModalData] = useState(null);

//UseEffects

useEffect(()=>{
  getTags();
  getTaskStates();
  getTasks();
},[])
    
const reloadTasks = () =>{
  getTasks();
}

const handleEditTask = (task) => {
  setModalData(task);
  setOpenModal(true);
}

const handleAddTask = () => {
  setModalData(null);
  setOpenModal(true);
}

  const updateTasks = async  () => {
    console.log("doing updateTasks")
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
  reloadTasks={reloadTasks}
  modalData={modalData}
  updateTasks={updateTasks}
  handleCloseModal={handleCloseModal}
  taskStates={taskStates}
  tags={tags} 
   />
</Modal>
        <Typography  variant="h3" style = {{margin:"8px"}}> My tasks:
        {tasks.length==0 && <Typography variant="h6">You have not tasks</Typography>}  
        </Typography>



        <Box className="main">
        <ul className="tasks">
        {tasks.map((task, index) => (
          <li key={task.id} className="task" >
          <Card className="card"
          >
            <CardContent>
              <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                Task
              </Typography>

              <div className="task-options">
              <button className="edit" onClick={()=>handleEditTask(task)}>Edit</button>
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
        <li style={{display:"flex", justifyContent:"center", alignItems:"center"}} >
          
       <Button  
      variant="contained"
      endIcon={<AddIcon />}
        onClick={handleAddTask}
      >add new task</Button>
      </li>
      </ul>
    
      </Box>
    

    
      </Box>
     );
}
 
export default MyTasks;