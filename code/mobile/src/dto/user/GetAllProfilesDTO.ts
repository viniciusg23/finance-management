export type GetAllProfilesDTO = {
    ownm: Profile[]
    shared: Profile[]
}

type Profile = {
    id: string;
    user: string;
    name: string;
    createdAt: string;
}