export interface IGetAllProfilesDTO {
    ownm: Profile[]
    shared: Profile[]
}

export type Profile = {
    id: string;
    user: string;
    name: string;
    createdAt: string;
}