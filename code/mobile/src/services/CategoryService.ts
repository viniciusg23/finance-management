import { CreateCategoryResponse } from "dto/category/CreateCategoryResponse";
import { CreateCategoryRequest } from "dto/category/CreateCategoryRequest";
import { UpdateCategoryRequest } from "dto/category/UpdateCategoryRequest";
import { Storage } from "shared/utils/Storage";
import { config } from "../../config/config";
import { handleHeaders } from "shared/utils/helperFunction";

export class CategoryService {
	public async createCategory(
		data: CreateCategoryRequest
	): Promise<CreateCategoryResponse> {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const raw = JSON.stringify(data);

		const requestOptions = {
			method: "POST",
			headers: headers,
			body: raw,
		};

		const response = await fetch(
			`${config.apiUrl}/category`,
			requestOptions
		);
		if (!response.ok) throw new Error("Erro ao criar carteira");
		return await response.json();
	}

	public async deleteCategory(categorytId: string) {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "DELETE",
			headers: headers,
		};

		const response = await fetch(
			`${config.apiUrl}/category/${categorytId}`,
			requestOptions
		);
		if (!response.ok) throw new Error("Erro ao deletar categoria");
		return true;
	}

	public async updateCategory(
		data: UpdateCategoryRequest
	): Promise<CreateCategoryResponse> {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const raw = JSON.stringify(data);

		const requestOptions = {
			method: "PUT",
			headers: headers,
			body: raw,
		};

		const response = await fetch(
			`${config.apiUrl}/category`,
			requestOptions
		);
		if (!response.ok) throw new Error("Erro ao atualizar a categoria.");
		return await response.json();
	}
}
