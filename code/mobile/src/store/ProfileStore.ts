import { create } from "zustand";

type Profile = {
    profileId: string | null;
    user?: string | null;
    name?: string | null;
    createdAt?: Date | null;
}

type State = {
    profileData: Profile
}

const initialState: State = {
    profileData: {
        profileId: null,
        user: null,
        name: null,
        createdAt: null,
    }
}

type Actions = {
    setProfileId: (data: string) => void
    setUser: (data: string) => void
    setName: (data: string) => void
    setCreatedAt: (data: Date) => void
    updateState:(profile: Profile) => void
    resetProfileData: () => void
}

export const useProfileStore = create<State & Actions>((set) => ({
    ...initialState,
    setProfileId: (data: string) => set((state) => ({ profileData: { ...state.profileData, profileId: data } })),
    setUser: (data: string) => set((state) => ({ profileData: { ...state.profileData, user: data } })),
    setName: (data: string) => set((state) => ({ profileData: { ...state.profileData, name: data } })),
    setCreatedAt: (data: Date) => set((state) => ({ profileData: { ...state.profileData, createdAt: data } })),
    updateState: (profile) => set(() => ({profileData: profile})),
    resetProfileData: () => set(initialState),
}))
