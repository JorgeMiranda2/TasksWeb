import { useEffect, useState } from "react";
import { BACKEND_PATH } from "../../Config/Constants";
import GetAPI from "../../Services/GetAPI";

const useTags = () => {

    const [tags, setTags] = useState([]);


const createFormatTags = (response) => {
    return response.map((tag)=>{
        return {
            id:tag.id,
            title:tag.name,
            description:tag.description
        }
    })
}

    const getTags = async () => {
        try{
      
            const response = await GetAPI(BACKEND_PATH + "/api/tag");
            setTags(createFormatTags(response));
        } catch(e){
            console.log("error getting tags")
        }
    
    }
    return {getTags,tags}
}
 
export default useTags;