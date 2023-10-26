import { Box, Button, Card, CardActions, CardContent, Modal, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import GetAPI from "../../Services/GetAPI";
import { BACKEND_PATH } from "../../Config/Constants";
import AddTaskModal from "./AddTaskModal";
import useTags from "../Hooks/useTags";
import useTasks from "../Hooks/useTasks";


const MyTasks = () => {

  const {getTasks, tasks} = useTasks();

    const [openModal, setOpenModal] = useState(false);




    useEffect(()=>{
      getTasks()
    },[])

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
        <Box>
<Modal
  open={openModal}
  onClose={handleCloseModal}
  aria-labelledby="modal-modal-title"
  aria-describedby="modal-modal-description"
>
  <AddTaskModal/>
</Modal>
        <h2  style = {{margin:"8px"}}>My tasks:</h2>
        <Box display="flex" flexWrap="wrap">
        {tasks.map((task, index) => (
          <Card
            sx={{
              width: 'calc(33.33% - 16px)',
              margin: '8px',
            }}
            key={index}
          >
            <CardContent>
              <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                Task
              </Typography>
              <Typography variant="h5" component="div">
                {task.title}
              </Typography>
              <Typography sx={{ mb: 1.5 }} color="text.secondary">
                {task.state}
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
      
        ))}
        <Button  
        onClick={()=> handleOpenModal()}
        sx={{
              width: 'calc(33.33% - 16px)',
              margin: '8px',
            }}>add task</Button>
      </Box>
      <Typography variant="h2">Tags:</Typography>
    
      </Box>
     );
}
 
export default MyTasks;