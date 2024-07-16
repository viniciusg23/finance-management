export interface IGetAllProfileWalletsResponseDTO {
    id: string;
    name: string;
    description: string;
    color: string;
    balance: number;
    icon: TUseIconResponseDTO;
    goalWallet: boolean;
    createdAt: string;
}

type TUseIconResponseDTO = {
    id: string;
    name: string;
    thridPartyId: string;
    createdAt: string;
}