const STORAGE_TOKEN_KEY = "jwt";
const STORAGE_USER_ID_KEY = "userId";

export class Storage {
	static getUserToken() {
		return localStorage.getItem(STORAGE_TOKEN_KEY);
	}
	static saveUserToken(token: string) {
		return localStorage.setItem(STORAGE_TOKEN_KEY, token);
	}
	static deleteUserToken() {
		return localStorage.removeItem(STORAGE_TOKEN_KEY);
	}
	static getUserId() {
		return localStorage.getItem(STORAGE_USER_ID_KEY);
	}
	static saveUserId(id: string) {
		return localStorage.setItem(STORAGE_USER_ID_KEY, id);
	}
	static deleteUserId() {
		return localStorage.removeItem(STORAGE_USER_ID_KEY);
	}
}
