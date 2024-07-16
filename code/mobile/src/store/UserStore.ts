import { create } from "zustand";

type User = {
	userId?: string | null;
	name?: string | null;
	nickname?: string | null;
	token?: string | null;
	email?: string | null;
	walletsCount?: number;
};

type UserState = {
	userData: User;
};

type UserActions = {
	setUserData: (data: User) => void;
	setName: (data: string) => void;
	setNick: (data: string) => void;
	setEmail: (data: string) => void;
	setToken: (data: string) => void;
	setWalletsCount: (data: number) => void;
    resetUserData: () => void;
};

const initialState: UserState = {
	userData: {
		userId: null,
		name: null,
		nickname: null,
		token: null,
		email: null,
		walletsCount: 0
	},
};

export const useUserStore = create<UserState & UserActions>()((set) => ({
	...initialState,
	setUserData: (data: User) => set({ userData: data }),
	setName: (data: string) =>
		set((state) => ({ userData: { ...state.userData, name: data } })),
	setNick: (data: string) =>
		set((state) => ({ userData: { ...state.userData, nickname: data } })),
	setEmail: (data: string) =>
		set((state) => ({ userData: { ...state.userData, email: data } })),
	setToken: (data: string) =>
		set((state) => ({ userData: { ...state.userData, token: data } })),
	setWalletsCount: (data: number) =>
		set((state) => ({ userData: { ...state.userData, walletsCount: (state.userData.walletsCount || 0) + data } })),
	resetUserData: () => set(initialState),
}));
