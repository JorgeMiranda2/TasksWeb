import axios from "axios";

const PostAPI = async (url , info) => {
    let config = {};
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
        const response = await axios.post(url,info,config);
        console.log("Doing Post Request");
   
        return response.data;
    } catch (error){
        console.log("error: " + error.message);
        window.localStorage.removeItem("userSession");
   
        return null;
    }
   
}
 
export default PostAPI;