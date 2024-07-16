export type CreateCategoryResponse = {
	id: string;
	profile: {
		id: string;
		creator: string;
		name: string;
		createdAt: string;
	};
	icon: {
		id: string;
		name: string;
		thirdPartyId: string;
		createdAt: string;
	};
	name: string;
	type: "CREDIT" | "DEBIT";
	createdAt: string;
};
