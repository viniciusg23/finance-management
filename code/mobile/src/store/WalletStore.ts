import { create } from "zustand";

type Icon = {
	id: string;
	name: string;
	thirdPartyId: string;
	createdAt: string;
};

type Profile = {
	id: string;
	creator: string;
	name: string;
	createdAt: string;
};

export type Wallet = {
	id: string;
	profile: Profile;
	name: string;
	description: string;
	color: string;
	balance?: number;
	icon: Icon;
	goalWallet: boolean;
	createdAt: string;
};

type WalletState = {
	walletData: Wallet[] | null;
};

type WalletActions = {
	setWalletData: (data: Wallet | Wallet[]) => void;
	setName: (newName: string, walletId: string) => void;
	setBalance: (newBalance: number, walletId: string) => void;
	setDescription: (newDescription: string, walletId: string) => void;
	setIcon: (newIcon: Icon, walletId: string) => void;
	resetUserData: () => void;
};

const initialState: WalletState = {
	walletData: null,
};

const updateWallet =
	(walletId: string, changes: Partial<Wallet>) => (get: any) => {
		const updatedWallets = get().walletData?.map((wallet: Wallet) =>
			wallet.id === walletId ? { ...wallet, ...changes } : wallet
		);
		return updatedWallets;
	};

export const useWalletStore = create<WalletState & WalletActions>()((set) => ({
	...initialState,
	setWalletData: (data: Wallet | Wallet[]) =>
		set((state) => ({
			...state,
			walletData: Array.isArray(data)
				? data
				: state.walletData
				? [...state.walletData, data]
				: [data],
		})),
	setName: (newName: string, walletId: string) =>
		set(updateWallet(walletId, { name: newName })),
	setBalance: (newBalance: number, walletId: string) =>
		set(updateWallet(walletId, { balance: newBalance })),
	setDescription: (newDescription: string, walletId: string) =>
		set(updateWallet(walletId, { description: newDescription })),
	setIcon: (newIcon: Icon, walletId: string) =>
		set(updateWallet(walletId, { icon: newIcon })),
	resetUserData: () => set(initialState),
}));
