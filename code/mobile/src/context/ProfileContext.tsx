import * as SecureStore from "expo-secure-store";
import { createContext, useContext, useEffect, useState } from "react";
import { Platform } from 'react-native';
import { LoginUserRequestDTO } from "dto/user/LoginUserRequestDTO";
import { SaveUserRequestDTO } from "dto/user/SaveUserRequestDTO";
import { UserService } from "services/UserService";
import { User } from "../models/User";

interface ProfileContext {
}

const profileContext = createContext<ProfileContext | undefined>(undefined);

export const useProfile = () => {
    const context = useContext(profileContext);
    if(!context)
        throw new Error('');
    return context;
}

export const ProfileProvider = ({ children }: any) => {
    
}