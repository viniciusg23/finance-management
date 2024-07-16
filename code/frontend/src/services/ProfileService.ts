import { IGetAllProfilesDTO } from "shared/dto/IGetAllProfilesDTO";
import { IGetAllUserInviteResponseDTO } from "shared/dto/IGetAllUserInviteResponseDTO";
import { Storage } from "shared/utils/Storage";
import { apiUrl } from "shared/utils/config";
import { handleHeaders } from "shared/utils/helperFunctions";


export class ProfileService {
	public static async getAllProfileWallets(profileId: string) {
		const token = await Storage.getUserToken();
		const headers = new Headers();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${apiUrl}/profile/wallets/${profileId}`,
			requestOptions
		);
		if (!response.ok) {
			console.log("erro");
			console.log(response);

			throw new Error("Erro ao buscar carteiras do perfil.");
		}

		return await response.json();
	};

    public static async getAllUserProfiles(): Promise<IGetAllProfilesDTO> {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${apiUrl}/user/profiles`,
			requestOptions
		);
		if (!response.ok) throw new Error("Erro ao carregar os perfis");

		return await response.json();
	};

	public static async getReceivedInvites(): Promise<IGetAllUserInviteResponseDTO> {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${apiUrl}/sharing/invite/received`,
			requestOptions
		);
		if (!response.ok) throw new Error("Erro ao carregar os perfis");

		return await response.json();
	}

	public static async getSentInvites(): Promise<IGetAllUserInviteResponseDTO> {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${apiUrl}/sharing/invite/received`,
			requestOptions
		);
		if (!response.ok) throw new Error("Erro ao carregar os perfis");

		return await response.json();
	}

	public static async getAllProfileCategories(profileId: string) {
		const token = await Storage.getUserToken();
		const headers = new Headers();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${apiUrl}/profile/categories/${profileId}`,
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
