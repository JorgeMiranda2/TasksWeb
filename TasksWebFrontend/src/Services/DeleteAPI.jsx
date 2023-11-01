
import axios from "axios";
import getRequestConfig from "../Helpers/GetRequestConfig";
const DeleteAPI = async (url) => {

const config = getRequestConfig();

try{

const response = await axios.delete(url, config);
console.log("Doing Delete Request");
return response.data;

} catch(error){
    console.log("error tryind to delete: " + error);
    return null;
}

}
 
export default DeleteAPI;