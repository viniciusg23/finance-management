import { useState } from "react";
import { ProfileService } from "services/ProfileService";
import { UserService } from "services/UserService";
import { IGetUserByIdDTO } from "shared/dto/IGetUserByIdDTO";
import { useAuthStore } from "shared/stores/Auth/AuthStore";
import { Storage } from "shared/utils/Storage";

export const useHome = () => {
    const [user, setUser] = useState<IGetUserByIdDTO>();
    const [invitesReceived, setInvitesReceived] = useState(0);
    const [invitesSent, setInvitesSent] = useState(0);
    const [profilesCounts, setProfilesCounts] = useState(0);
    const { userData, setProfilesCount } = useAuthStore();

    const handleUserData = async() => {
        let id = userData.userId;
        if(!id){
          id = Storage.getUserId();
        }
        const response = await UserService.getUserById(id!);
        if(response){
          setUser(response)
        }
      }
    
    const handleLoadUserProfilesCount = async() => {
        const response = await ProfileService.getAllUserProfiles();
        if(response){
            setProfilesCounts(response.ownm.length + response.shared.length);
            setProfilesCount(response.ownm.length + response.shared.length);
        }
    }

    const handleLoadReceivedProfileInvites = async() => {
        const response = await ProfileService.getReceivedInvites();
        if(response){
            setInvitesReceived(invitesReceived + response.invites.length);
        }
    }

    const handleLoadAllProfileSentInvites = async() => {
        const response = await ProfileService.getSentInvites();
        if(response){
            setInvitesSent(invitesSent + response.invites.length);
        }
    }

    return {
        user,
        profilesCounts,
        invitesReceived,
        invitesSent,
        handleUserData,
        handleLoadReceivedProfileInvites,
        handleLoadAllProfileSentInvites,
        handleLoadUserProfilesCount,
    }
}