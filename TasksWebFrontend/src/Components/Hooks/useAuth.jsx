import { useDispatch, useSelector } from "react-redux";
import PostAPI from "../../Services/PostAPI";
import { login } from "../../redux/AuthSlice";
import { BACKEND_PATH } from "../../Config/Constants";

const useAuth = () => {
    const dispatch = useDispatch();
    const authValues = useSelector(state => state.auth);

    const registerUser = async (values) => {
        PostAPI(BACKEND_PATH + "/auth/register", values)
        .then((userResponse) => {
         if(userResponse){
          localStorage.setItem("userSession", JSON.stringify(userResponse));
          console.table(userResponse);
         dispatch(login({auth:true, username:userResponse.userName,token:userResponse.token}));
          console.table(authValues);
           //window.location.href="/";
         }
        })
    }
    return {registerUser};
}
 
export default useAuth;