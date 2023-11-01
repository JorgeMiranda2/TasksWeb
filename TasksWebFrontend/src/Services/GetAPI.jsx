import axios from "axios";
import getRequestConfig from "../Helpers/GetRequestConfig";

const GetAPI = async (url) => {

    const config = getRequestConfig();
    try{   
        const response = await axios.get(url,config);
        console.log("Doing Get Request");
   
        return response.data;
    } catch (error){
        console.log("error: " + error.message);
     //   window.localStorage.removeItem("userSession");
        return null;
    }
   
}
 
export default GetAPI;