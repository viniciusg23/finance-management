import { SharingInviteStatusEnum } from "shared/types/SharingInviteStatusEnum";
import { IUseProfileResponseDTO } from "./IUseProfileResponseDTO";
import { IUseUserResponseDTO } from "./IUseUserResponseDTO";

export interface IGetUserInviteResponseDTO {
    id: string;
	status: SharingInviteStatusEnum;
	sender: IUseUserResponseDTO;
	receiver: IUseUserResponseDTO;
	profile: IUseProfileResponseDTO;
    createdAt: string;
}
