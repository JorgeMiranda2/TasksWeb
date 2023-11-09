# TasksWeb
User Registration and Note Reminder Application

# Overview
This project is an application that allows users to register and create reminder notes associated with their accounts. Users can manage these notes by editing or deleting them based on their preferences.

Technologies Used
Frontend
React JS with Vite
Redux for state management
Material UI for UI components
Formik for form handling
Yup for schema validation
Backend
Spring Boot
Spring Security for JWT token management
Hibernate for database interaction
Lombok for boilerplate code reduction
Communication
Communication between the frontend and backend is facilitated through RESTful APIs in JSON format.

Database
The application uses MySQL as its database to store user information and reminder notes.

Getting Started
Follow the steps below to set up and run the application locally:

Clone the Repository

# Frontend Setup:

Copy code in Bash:

cd frontend

npm install

npm run dev

 # Backend Setup:

Copy code in Bash:

cd backend

./mvnw spring-boot:run

Access the Application:

Open your web browser and go to http://localhost:3000 to access the application.

# Features
User Registration: Allows users to register accounts.

Note Creation: Users can create reminder notes associated with their accounts.

Note Management: Users can edit or delete existing notes.


# License
This project is licensed under the MIT License. Feel free to use, modify, and distribute the code as per the license terms.

# Notes
In the table profile of the database is necessary create at least 2 register with id = 1 for user and id = 2 for admin for the spring security to work

