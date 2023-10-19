import { Box, Button } from "@mui/material";
import { useFormik } from 'formik';
import * as Yup from 'yup';
const AddTaskModal = () => {


const formik = useFormik({
    initialValues: {
      dateTime: '',
    },
    validationSchema: Yup.object({
      dateTime: Yup.date().required('La fecha y hora son obligatorias'),
    }),
    onSubmit: (values) => {
      // Aquí puedes manejar la acción después de enviar el formulario
      console.log('Fecha y hora seleccionadas:', values.dateTime);
    },
  });

  return (
    <Box   sx={{
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '80%', // Ajusta el ancho del modal
        maxWidth: 400, // Ancho máximo del modal
        bgcolor: 'white',
        border: '2px solid #000',
        borderRadius: '8px',
        boxShadow: 24,
        p: 4,
        textAlign: 'center',
      }} >
    <form onSubmit={formik.handleSubmit}>
  
        <input
          type="datetime-local"
          id="dateTime"
          name="dateTime"
          value={formik.values.dateTime}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
  

  <div style={{ height: '20px' }}>
        {formik.touched.dateTime && formik.errors.dateTime ? (
          <div style={{ color: 'red' }}>{formik.errors.dateTime}</div>
        ) : null}
      </div>
<Button type="submit"> Add New Task</Button>
    </form>
    </Box>
  );
}
 
export default AddTaskModal;