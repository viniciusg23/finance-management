import { SaveUserRequestDTO } from "dto/user/SaveUserRequestDTO";
import { config } from "../../config/config";
import { Storage } from "shared/utils/Storage";
import { GetUserByIdResponse } from "dto/user/GetUserByIdResponse";
import { LoginUserRequestDTO } from "dto/user/LoginUserRequestDTO";
import { LoginUserResponseDTO } from "dto/user/LoginUserResponseDTO";
import { SaveUserResponseDTO } from "dto/user/SaveUserResponseDTO";
import { GetAllProfilesDTO } from "dto/user/GetAllProfilesDTO";
import { handleHeaders } from "shared/utils/helperFunction";

export class UserService {
	public async createUser(
		data: SaveUserRequestDTO
	): Promise<SaveUserResponseDTO> {
		const headers = new Headers();
		handleHeaders(headers);

		const raw = JSON.stringify({
			name: data.name,
			nickname: data.nickname,
			email: data.email,
			password: data.password,
		});

		const requestOptions = {
			method: "POST",
			headers: headers,
			body: raw,
		};

		const response = await fetch(`${config.apiUrl}/user`, requestOptions);
		if (!response.ok) {
			console.log(response);
			throw new Error("Erro ao criar usuário");
		}

		return await response.json();
	}

	public async login(
		data: LoginUserRequestDTO
	): Promise<LoginUserResponseDTO> {
		const headers = new Headers();
		handleHeaders(headers);

		const raw = JSON.stringify({
			email: data.email,
			password: data.password,
		});

		const requestOptions = {
			method: "POST",
			headers: headers,
			body: raw,
		};

		const response = await fetch(
			`${config.apiUrl}/user/login`,
			requestOptions
		);
		if (!response.ok) {
			throw new Error("Erro ao fazer login");
		}
		return await response.json();
	}

	public async getUserData(userId: string): Promise<GetUserByIdResponse> {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${config.apiUrl}/user/${userId}`,
			requestOptions
		);
		if (!response.ok) {
			throw new Error("Erro ao obter os dados do Usuário");
		}

		return await response.json();
	}

	public async getAllUserProfiles(): Promise<GetAllProfilesDTO> {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${config.apiUrl}/user/profiles`,
			requestOptions
		);
		if (!response.ok) throw new Error("Erro ao carregar os perfis");

		return await response.json();
	}
}
