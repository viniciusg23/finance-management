export type CreateWalletResponse = {
    id: string;
    profile: {
        id: string;
        creator: string;
        name: string;
        createdAt: string;
    }
    icon: {
        id: string;
        name: string;
        thirdPartyId: string;
        createdAt: string;
    },
    name: string;
    description: string;
    color: string;
    goalWallet: boolean;
    createdAt: string;
}