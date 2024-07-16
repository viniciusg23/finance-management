import { Storage } from "shared/utils/Storage";
import { config } from "../../config/config";
import { CreateProfileResponse } from "dto/profile/CreateProfileResponse";
import { handleHeaders } from "shared/utils/helperFunction";

export class ProfileService {
	public static async createProfile(
		name: string
	): Promise<CreateProfileResponse> {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const raw = JSON.stringify({
			name: name,
		});

		const requestOptions = {
			method: "POST",
			headers: headers,
			body: raw,
		};

		const response = await fetch(
			`${config.apiUrl}/profile`,
			requestOptions
		);
		if (!response.ok) {
			throw new Error("Erro ao criar perfil");
		}

		return await response.json();
	}

	public static async deleteProfile(profileId: string) {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "DELETE",
			headers: headers,
		};

		const response = await fetch(
			`${config.apiUrl}/profile/${profileId}`,
			requestOptions
		);
		if (!response.ok) {
			throw new Error("Erro ao excluir perfil.");
		}

		return true;
	}

	public async getAllProfileWallets(profileId: string) {
		const token = await Storage.getUserToken();
		const headers = new Headers();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${config.apiUrl}/profile/wallets/${profileId}`,
			requestOptions
		);
		if (!response.ok) {
			console.log("erro");
			console.log(response);

			throw new Error("Erro ao buscar carteiras do perfil.");
		}

		return await response.json();
	}
	
	// public async getAllProfileTransactions(profileId: string) {
	// 	const token = await Storage.getUserToken();
	// 	const headers = new Headers();
	// 	handleHeaders(headers, token);

	// 	const requestOptions = {
	// 		method: "GET",
	// 		headers: headers,
	// 	};

	// 	const response = await fetch(
	// 		`${config.apiUrl}/profile/transactions/${profileId}`,
	// 		requestOptions
	// 	);
	// 	if (!response.ok) {
	// 		console.log("erro");
	// 		console.log(response);

	// 		throw new Error("Erro ao buscar carteiras do perfil.");
	// 	}

	// 	return await response.json();
	// }
	
	public async getAllProfileCategories(profileId: string) {
		const token = await Storage.getUserToken();
		const headers = new Headers();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${config.apiUrl}/profile/categories/${profileId}`,
			requestOptions
		);
		if (!response.ok) {
			console.log("erro");
			console.log(response);

			throw new Error("Erro ao buscar carteiras do perfil.");
		}

		return await response.json();
	}
}
