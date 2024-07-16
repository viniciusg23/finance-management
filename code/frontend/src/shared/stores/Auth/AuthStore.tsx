import { create } from "zustand";

type User = {
	userId?: string | null;
	name?: string | null;
	nickname?: string | null;
	token?: string | null;
	email?: string | null;
	profilesCount?: number;
};

type UserState = {
	userData: User;
};

type UserActions = {
	setUserData: (data: User) => void;
	setId: (data: string) => void;
	setName: (data: string) => void;
	setNickname: (data: string) => void;
	setEmail: (data: string) => void;
	setToken: (data: string) => void;
	setProfilesCount: (data: number) => void;
    resetUserData: () => void;
};

const initialState: UserState = {
	userData: {
		userId: null,
		name: null,
		nickname: null,
		token: null,
		email: null,
		profilesCount: 0
	},
};

export const useAuthStore = create<UserState & UserActions>()((set) => ({
	...initialState,
	setUserData: (data: User) => set({ userData: data }),
	setId: (data: string) =>
		set((state) => ({ userData: { ...state.userData, id: data } })),
	setName: (data: string) =>
		set((state) => ({ userData: { ...state.userData, name: data } })),
	setNickname: (data: string) =>
		set((state) => ({ userData: { ...state.userData, nickname: data } })),
	setEmail: (data: string) =>
		set((state) => ({ userData: { ...state.userData, email: data } })),
	setToken: (data: string) =>
		set((state) => ({ userData: { ...state.userData, token: data } })),
	setProfilesCount: (data: number) =>
		set((state) => ({ userData: { ...state.userData, profilesCount: (state.userData.profilesCount || 0) + data } })),
	resetUserData: () => set(initialState),
}));
