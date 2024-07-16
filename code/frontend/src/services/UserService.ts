import { IGetUserByIdDTO } from 'shared/dto/IGetUserByIdDTO';
import { IUserLoginDTO } from 'shared/dto/IUserLoginDTO';
import { Storage } from 'shared/utils/Storage';
import { apiUrl } from 'shared/utils/config';
import { handleHeaders } from 'shared/utils/helperFunctions';

export class UserService {
    static async onLogin(email: string, password: string): Promise<IUserLoginDTO | undefined> {

		const headers = new Headers();
		handleHeaders(headers);

		const raw = JSON.stringify({
			email: email,
			password: password
		});

		const requestOptions = {
			method: "POST",
			headers: headers,
			body: raw,
		};

		const response = await fetch(`${apiUrl}/user/login`, requestOptions);
		if (!response.ok) {
			throw new Error("Erro ao fazer login");
		}
		return await response.json();
    }

	static async getUserById(userId: string): Promise<IGetUserByIdDTO> {
		const headers = new Headers();
		const token = Storage.getUserToken();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "GET",
			headers: headers,
		};

		const response = await fetch(
			`${apiUrl}/user/${userId}`,
			requestOptions
		);
		if (!response.ok) {
			throw new Error("Erro ao obter os dados do Usuário");
		}

		return await response.json();
	}
}