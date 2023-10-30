import { Box, Button } from "@mui/material";
import { useFormik } from 'formik';
import * as Yup from 'yup';
import useTags from "../Hooks/useTags";
import "../../Css/Components/AddTaskModal.css";
import useTaskStates from "../Hooks/useTaskStates";
import { useEffect, useState } from "react";
import useTasks from "../Hooks/useTasks";


const AddTaskModal = ( {handleCloseModal , updateTasks}) => {

//Custom Hooks
const {tags, getTags} = useTags();
const {taskStates, getTaskStates} = useTaskStates();
const {sendTask} = useTasks();

//Hooks
const [tagsToAdd, setTagsToAdd] = useState([]);

//UseEffects

useEffect(()=>{
  getTags();
  getTaskStates()
},[])
    
const handleAddTag = (tag) => {
setTagsToAdd([...tagsToAdd, tag]);
console.log(tagsToAdd);
}

const formik = useFormik({
    initialValues: {
      startDateTime: '',
      endDateTime: '',
      title:'',
      description:'',
      taskState:'',
      tags:tagsToAdd,

    },
    validationSchema: Yup.object({
      startDateTime: Yup.date().required('Date is obligatory'),
      endDateTime: Yup.date().required('Date is obligatory'),
      title:Yup.string().required('title is obligatory'),
      description:Yup.string().required('title is obligatory'),
      tags: Yup.array(),
    }),
    onSubmit: async (values) => {
      await sendTask(values);
      await updateTasks();
  
     handleCloseModal();

    },
  });

  return (
    <Box   sx={{
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '80%', // Ajusta el ancho del modal
        maxWidth: 600, // Ancho máximo del modal
        bgcolor: 'white',
        border: '2px solid #000',
        borderRadius: '8px',
        boxShadow: 24,
        p: 4,
      
      }} >
    <form className="taskForm" onSubmit={formik.handleSubmit} >
<label>Title:</label>
      <input type="text" style={{width:"50%"}}name="title" id="title" placeholder="Do Something" 
      value={formik.values.title}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur} />
      {formik.touched.title && formik.errors.title ? (
          <label style={{ color: 'red' }}>title error</label>
        ) : null}

<label>
  Description:
  </label>
          <textarea type="" style={{width:"50%"}} name="description" id="description" placeholder="I have to..." 
      value={formik.values.description}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur} />

      {formik.touched.description && formik.errors.description ? (
          <label style={{ color: 'red' }}>description error</label>
        ) : null}

    <div className="dates">
    <label>Start: </label>
        <input
          type="datetime-local"
          id="startDateTime"
          name="startDateTime"
          value={formik.values.startDateTime}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />

           {formik.touched.startDateTime && formik.errors.startDateTime ? (
          <label className="error" style={{ color: 'red' }}>Date error</label>
        ) : null}
    

<label>End: </label>
      <input
          type="datetime-local"
          id="endDateTime"
          name="endDateTime"
          value={formik.values.endDateTime}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
        
        {formik.touched.endDateTime && formik.errors.endDateTime ? (
          <label className="error" style={{ color: 'red' }}>Date error</label>
        ) : null}

     
        </div>
     
      <label>Task state: </label>
        <select name="taskState" id="taskState">
         {taskStates.map((taskState)=>{
          return <option key={taskState.id} title={taskState.description} value="xd">{taskState.name}</option>
         })}
        </select>
        {formik.touched.taskState && formik.errors.taskState ? (
          <label className="error" style={{ color: 'red' }}>state error</label>
        ) : null}

       
        <label>Tags: </label>
        <select name="tags"  id="tags">
        {tags.map((tag)=>{
          return (
           <option  value={tag.title} 
           style= {{color:"black"}} 
           key={tag.id} 
           title={tag.description}
           >
            {tag.title}
            </option>
         
          )
        })}
      </select>
      {formik.touched.tags && formik.errors.tags ? (
          <label className="error" style={{ color: 'red' }}>state error</label>
        ) : null}


<Button type="submit"> Add New Task</Button>
    </form>
    </Box>
  );
}
 
export default AddTaskModal;