
import { ProfileService } from "services/ProfileService";
import { IGetAllProfilesDTO } from "shared/dto/IGetAllProfilesDTO";

export const useProfiles = () => {

    const handleGetProfiles = async(setProfiles: (profiles: IGetAllProfilesDTO) => void) => {
        const response = await ProfileService.getAllUserProfiles();
        if(response){
          setProfiles(response);
        }
    }
    return {
        handleGetProfiles
    }
}