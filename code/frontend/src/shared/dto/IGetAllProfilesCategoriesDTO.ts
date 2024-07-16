export interface IGetAllProfilesCategoriesDTO {
    categories: Category[]
}

type Category = {
    id: string;
    name: string;
    icon: Icon;
    type: string;
    createdAt: string;
}

type Icon = {
    id: string;
    name: string;
    thirdPartId: string;
    createdAt: string;
}