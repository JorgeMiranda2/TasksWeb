

import axios from "axios";
import getRequestConfig from "../Helpers/GetRequestConfig";

const PutAPI = async  (url,params) => {
   
const config = getRequestConfig();


try{

const response = await axios.put(url, params, config);
console.log("Doing Update Request");
return response.data;

} catch(error){
    console.log("error trying to update: " + error);
    return null;
}
}
 
export default PutAPI;