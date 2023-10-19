import axios from "axios";

const GetAPI = async (url) => {
    let config = {
    };
    const StringUserSession = window.localStorage.getItem("userSession");

    if(StringUserSession){
        const user = JSON.parse(StringUserSession);
   
        config = {
            headers:{
                Authorization:`Bearer ${user?.token}`
            }
        }

    
    }

    try{   
        const response = await axios.get(url,config);
        console.log("Doing Get Request");
   
        return response.data;
    } catch (error){
        console.log("error: " + error.message);
        window.localStorage.removeItem("userSession");
        return null;
    }
   
}
 
export default GetAPI;