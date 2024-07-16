import { create } from "zustand";

type Icon = {
	id: string;
	name: string;
	thirdPartyId: string;
	createdAt: string;
};

export type Category = {
	id: string;
	name: string;
	icon: Icon;
	type: "CREDIT" | "DEBIT";
	createdAt: string;
};

type CategoryState = {
	categoriesData: Category[] | null;
};

type CategoryActions = {
	setCategoryData: (data: Category | Category[]) => void;
	setName: (newName: string, categoryId: string) => void;
	setIcon: (newIcon: Icon, categoryId: string) => void;
	resetUserData: () => void;
};

const initialState: CategoryState = {
	categoriesData: null,
};

const updateCategory =
	(categoryId: string, changes: Partial<Category>) => (get: any) => {
		const updatedCategory = get().categoriesData?.map(
			(categories: Category) =>
				categories.id === categoryId
					? { ...categories, ...changes }
					: categories
		);
		return updatedCategory;
	};

export const useCategoryStore = create<CategoryState & CategoryActions>()(
	(set) => ({
		...initialState,
		setCategoryData: (data: Category | Category[]) =>
			set((state) => ({
				...state,
				categoriesData: Array.isArray(data)
					? data
					: state.categoriesData
					? [...state.categoriesData, data]
					: [data],
			})),
		setName: (newName: string, categoryId: string) =>
			set(updateCategory(categoryId, { name: newName })),
		setIcon: (newIcon: Icon, categoryId: string) =>
			set(updateCategory(categoryId, { icon: newIcon })),
		resetUserData: () => set(initialState),
	})
);
