import axios from "axios";
import getRequestConfig from "../Helpers/GetRequestConfig";
const PostAPI = async (url , info) => {

const config = getRequestConfig();

    try{   
        const response = await axios.post(url,info,config);
        console.log("Doing Post Request");
   
        return response.data;
    } catch (error){
        console.log("error: " + error.message);   
        return null;
    }
   
}
 
export default PostAPI;