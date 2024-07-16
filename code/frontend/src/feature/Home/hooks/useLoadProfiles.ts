import { useState } from "react"
import { ProfileService } from "services/ProfileService";
import { IGetAllProfilesDTO } from "shared/dto/IGetAllProfilesDTO";

export const useLoadProfiles = () => {
    const [profiles, setProfiles] = useState<IGetAllProfilesDTO>();

    const handleGetProfiles = async() => {
        const response = await ProfileService.getAllUserProfiles();
        if(response){
          setProfiles(response);
        }
    }
    return {
        handleGetProfiles,
        profiles
    }
}