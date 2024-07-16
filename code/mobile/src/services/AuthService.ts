import { Platform } from "react-native";
import * as SecureStore from "expo-secure-store";
import { UserService } from "./UserService";
import { LoginUserRequestDTO } from "dto/user/LoginUserRequestDTO";
import { SaveUserRequestDTO } from "dto/user/SaveUserRequestDTO";
import { LoginUserResponseDTO } from "dto/user/LoginUserResponseDTO";
import { GetUserByIdResponse } from "dto/user/GetUserByIdResponse";
import { SaveUserResponseDTO } from "dto/user/SaveUserResponseDTO";

type TAuthService = {
	loadToken: () => Promise<string | null>;
	loadUser: () => Promise<GetUserByIdResponse | null>;
	onLogin(data: LoginUserRequestDTO): Promise<LoginUserResponseDTO | string>;
	onRegister(data: SaveUserRequestDTO): Promise<SaveUserResponseDTO | string>;
	onLogout(): Promise<boolean>;
};

export const AuthService: TAuthService = {
	loadToken: async () => {
		let token: string | null;
		if (Platform.OS === "web") {
			token = await localStorage.getItem("jwt");
		} else {
			token = await SecureStore.getItemAsync("jwt");
		}
		return token;
	},
	loadUser: async () => {
		const userService = new UserService();
		let userId: string | null;
		if (Platform.OS === "web") {
			userId = await localStorage.getItem("userId");
		} else {
			userId = await SecureStore.getItemAsync("userId");
		}
		if (userId) {
			const user = await userService.getUserData(userId);
			return user;
		}
		return null;
	},
	onLogin: async (data: LoginUserRequestDTO) => {
		const userService = new UserService();
		try {
			const response = await userService.login(data);
			if (Platform.OS === "web") {
				localStorage.setItem("jwt", response.token);
				localStorage.setItem("userId", response.id);
			} else {
				SecureStore.setItemAsync("jwt", response.token);
				SecureStore.setItemAsync("userId", response.id);
			}
			return response;
		} catch (error) {
			const e = error as Error;
			return e.message;
		}
	},
	onRegister: async (data: SaveUserRequestDTO) => {
		const userService = new UserService();
		try {
			const response = await userService.createUser(data);
			return response;
		} catch (error) {
			const e = error as Error;
			return e.message;
		}
	},
	onLogout: async () => {
		if (Platform.OS === "web") {
			await localStorage.removeItem("jwt");
			await localStorage.removeItem("userId");
		} else {
			await SecureStore.deleteItemAsync("jwt");
			await SecureStore.deleteItemAsync("userId");
		}
		return true;
	},
};

export default AuthService;
