export type UpdateWalletRequest = {
	id: string;
	iconId: string;
	name: string;
	description: string;
	color: string;
	goalWallet: boolean;
    balance: number;
};
