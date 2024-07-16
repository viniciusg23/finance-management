import { CreateWalletResponse } from "dto/wallet/CreateWalletReponse";
import { CreateWalletRequest } from "dto/wallet/CreateWalletRequest";
import { Storage } from "shared/utils/Storage";
import { config } from "../../config/config";
import { UpdateWalletRequest } from "dto/wallet/UpdateWalletRequest";
import { handleHeaders } from "shared/utils/helperFunction";
import { Wallet } from "store/WalletStore";

export class WalletService {
	public async createWallet(data: CreateWalletRequest): Promise<Wallet> {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const raw = JSON.stringify(data);

		const requestOptions = {
			method: "POST",
			headers: headers,
			body: raw,
		};

		const response = await fetch(`${config.apiUrl}/wallet`, requestOptions);
		if (!response.ok) throw new Error("Erro ao criar carteira");
		return await response.json();
	}

	public async deleteWallet(walletId: string) {
		const headers = new Headers();
		const token = await Storage.getUserToken();
		handleHeaders(headers, token);

		const requestOptions = {
			method: "DELETE",
			headers: headers,
		};

		const response = await fetch(
			`${config.apiUrl}/wallet/${walletId}`,
			requestOptions
		);
		if (!response.ok) throw new Error("Erro ao criar carteira");
		return true;
	}

	public async getWalletById(walletId: string) {
		const token = await Storage.getUserToken();
		const headers = new Headers();
		headers.append("ngrok-skip-browser-warning", "123");
		headers.append("Content-Type", "application/json");
		if (token) headers.append("Authorization", token);

		// TODO
	}

	public async updateWallet(
		data: UpdateWalletRequest
	): Promise<CreateWalletResponse> {
		const token = await Storage.getUserToken();
		const headers = new Headers();
		headers.append("ngrok-skip-browser-warning", "123");
		headers.append("Content-Type", "application/json");
		if (token) headers.append("Authorization", token);

		const raw = JSON.stringify(data);

		const requestOptions = {
			method: "PUT",
			headers: headers,
			body: raw,
		};

		const response = await fetch(`${config.apiUrl}/wallet`, requestOptions);
		if (!response.ok) throw new Error("Erro ao atualizar a carteira.");
		return await response.json();
	}
}
