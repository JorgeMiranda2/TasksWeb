import { Box, Button } from "@mui/material";
import { useFormik } from 'formik';
import * as Yup from 'yup';
import useTags from "../Hooks/useTags";
import { useEffect } from "react";
const AddTaskModal = () => {
const {tags, getTags} = useTags();

    
useEffect(()=>{
  getTags();
},[])

const formik = useFormik({
    initialValues: {
      startDateTime: '',
      endDateTime: '',
      title:'',
      description:''
    },
    validationSchema: Yup.object({
      startDateTime: Yup.date().required('Date is obligatory'),
      endDateTime: Yup.date().required('Date is obligatory'),
      title:Yup.string().required('title is obligatory'),
      description:Yup.string().required('title is obligatory'),
    }),
    onSubmit: (values) => {
      // Aquí puedes manejar la acción después de enviar el formulario
      console.log('Fecha y hora seleccionadas:', values.startDateTime);
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
        textAlign: 'center',
        display:"flex",
        alignItems:"center",
        justifyContent:"center",
        verticalAlign:"middle"
      }} >
    <form onSubmit={formik.handleSubmit} >

      <input type="text" style={{width:"50%"}}name="title" id="title" placeholder="Do Something" 
      value={formik.values.title}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur} />
      {formik.touched.title && formik.errors.title ? (
          <label style={{ color: 'red' }}>title error</label>
        ) : null}

          <textarea type="" style={{width:"50%"}} name="description" id="description" placeholder="I have to..." 
      value={formik.values.description}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur} />

      {formik.touched.description && formik.errors.description ? (
          <label style={{ color: 'red' }}>description error</label>
        ) : null}
        <input
          type="datetime-local"
          id="startDateTime"
          name="startDateTime"
          value={formik.values.startDateTime}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
  <div style={{ height: '20px' }}>
        {formik.touched.startDateTime && formik.errors.startDateTime ? (
          <label style={{ color: 'red' }}>Date error</label>
        ) : null}
      </div>


      <input
          type="datetime-local"
          id="endDateTime"
          name="endDateTime"
          value={formik.values.endDateTime}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
  <div style={{ height: '20px' }}>
        {formik.touched.endDateTime && formik.errors.endDateTime ? (
          <label style={{ color: 'red' }}>Date error</label>
        ) : null}

      </div>


        <ul >
        {tags.map((tag)=>{
          return (
            <li style= {{color:"black"}} key={tag.id} title={tag.description}>{tag.title}</li>
         
          )
        })}
      </ul>


<Button type="submit"> Add New Task</Button>
    </form>
    </Box>
  );
}
 
export default AddTaskModal;