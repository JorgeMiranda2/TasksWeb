import { Box, Button } from "@mui/material";
import { useFormik } from "formik";
import * as Yup from "yup";
import Select from "react-select";
import "../../Css/Components/AddTaskModal.css";

import useTasks from "../Hooks/useTasks";
import { useCallback, useMemo } from "react";

const AddTaskModal = ({
  handleCloseModal,
  taskStates,
  tags,
  modalData,
  reloadTasks,
}) => {

  //Custom Hooks
  const { sendTask, updateTask } = useTasks();


  const adaptedOptions = useMemo(() => {
    return tags.map((tag) => {
      return {
        value: tag.id,
        label: tag.title,
      };
    });
  }, []);

  const getDefaultTags = useMemo(() => {
    if (modalData) {
      const values = modalData.tags.map((tag) => {
        return {
          value: tag.id,
          label: tag.name,
        };
      });
      return values;
    } else {
      return null;
    }
  }, []);

  const handleSelectChange = useCallback((selectedOptions) => {
    const selectedOptionsIds = selectedOptions.map(
      (selectedOption) => selectedOption.value
    );
    formik.setFieldValue("tags", selectedOptionsIds);
    console.table(formik.values.tags);
  }, []);

  const formik = useFormik({
    initialValues: {
      startDateTime: modalData?.start || "",
      endDateTime: modalData?.end || "",
      title: modalData?.title || "",
      description: modalData?.description || "",
      taskState: modalData?.state.id || "",
      tags: modalData?.tags.map((tag) => tag.id) || [],
    },
    validationSchema: Yup.object({
      startDateTime: Yup.date().required("Date is obligatory"),
      endDateTime: Yup.date().required("Date is obligatory"),
      title: Yup.string().required("title is obligatory"),
      description: Yup.string().required("title is obligatory"),
      taskState: Yup.string().required("need select one"),
      tags: Yup.array(),
    }),
    onSubmit: async (values) => {
      modalData
        ? await updateTask(modalData.id, values)
        : await sendTask(values);
      reloadTasks();
      handleCloseModal();
    },
  });

  return (
    <Box className="modal">
      <form className="taskForm" onSubmit={formik.handleSubmit}>
        <div className="input-container">
          <label>Title:</label>
          <input
            type="text"
            style={{ width: "50%" }}
            name="title"
            id="title"
            placeholder="Do Something"
            value={formik.values.title}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
          />
          {formik.touched.title && formik.errors.title ? (
            <label style={{ color: "red" }}>title error</label>
          ) : null}
        </div>
        <div className="input-container">
          <label>Description:</label>
          <textarea
            type=""
            style={{ width: "50%" }}
            name="description"
            id="description"
            placeholder="I have to..."
            value={formik.values.description}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
          />

          {formik.touched.description && formik.errors.description ? (
            <label>description error</label>
          ) : null}
        </div>

        <div className="dates">
          <div className="input-container">
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
              <label className="error" style={{ color: "red" }}>
                Date error
              </label>
            ) : null}
          </div>

          <div className="input-container">
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
              <label className="error" style={{ color: "red" }}>
                Date error
              </label>
            ) : null}
          </div>
        </div>
        <div className="input-container">
          <label>Task state: </label>
          <select
            value={formik.values.taskState}
            onChange={formik.handleChange}
            onBlur={formik.handleBlur}
            name="taskState"
            id="taskState"
          >
            <option value="" disabled>
              Select a state
            </option>
            {taskStates.map((taskState) => {
              return (
                <option
                  key={taskState.id}
                  value={taskState.id}
                  title={taskState.description}
                >
                  {taskState.name}{" "}
                </option>
              );
            })}
          </select>

          {formik.touched.taskState && formik.errors.taskState ? (
            <label className="error" style={{ color: "red" }}>
              state error
            </label>
          ) : null}
        </div>
        <div className="input-container">
          <label>Tags: </label>
          <Select
          className="multi-select"
            options={adaptedOptions}
            defaultValue={getDefaultTags}
            onChange={handleSelectChange}
            isMulti
          />
        </div>

        <div className="buttons-container">

          <Button variant="outlined" onClick={handleCloseModal}> Cancel</Button>
          <Button variant="contained" type="submit"> Confirm </Button>
        </div>
      </form>
    </Box>
  );
};

export default AddTaskModal;
