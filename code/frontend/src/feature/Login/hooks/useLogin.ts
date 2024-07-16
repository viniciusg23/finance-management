import { useState } from "react";
import { UserService } from "services/UserService";
import { useAuthStore } from "shared/stores/Auth/AuthStore";
import { useNavigate } from 'react-router-dom';
import { Storage } from "shared/utils/Storage";

export const useLogin = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const { setToken, setId, setName, setNickname } = useAuthStore();
    const navigate = useNavigate();

    const handleLogin = async (event: React.FormEvent, email: string, password: string, setError: React.Dispatch<React.SetStateAction<string>>) => {
        event.preventDefault();
        try{
            const response = await UserService.onLogin(email, password);
            if (response) {
                setId(response.id);
                setName(response.name);
                setNickname(response.nickname);
                setToken(response.token);
                Storage.saveUserToken(response.token);
                Storage.saveUserId(response.id);
                navigate('/home');
            }
        }catch (error){
            setError((error as Error).message)
        }
    }

    return {
        email,
        setEmail,
        password, 
        setPassword,
        error, 
        setError,
        handleLogin
    }
}