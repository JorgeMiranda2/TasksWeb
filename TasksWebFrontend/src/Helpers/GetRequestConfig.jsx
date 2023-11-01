

const getRequestConfig = () => {

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
        return config;
    }
 
export default getRequestConfig;