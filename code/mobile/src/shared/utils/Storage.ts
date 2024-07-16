import * as SecureStore from 'expo-secure-store';
import { Platform } from 'react-native';

const STORAGE_TOKEN_KEY = 'jwt';
const STORAGE_USER_ID_KEY = 'userId';
const STORAGE_PROFILE_ID_KEY = 'profileId';

export class Storage {
  static async getUserToken() {
    if(Platform.OS === "web")
        return await localStorage.getItem(STORAGE_TOKEN_KEY);
    else
        return await SecureStore.getItemAsync(STORAGE_TOKEN_KEY);
  }
  static async saveUserToken(token: string) {
    if(Platform.OS === "web")
        return await localStorage.setItem(STORAGE_TOKEN_KEY, token);
    else
        return await SecureStore.setItem(STORAGE_TOKEN_KEY, token);
  }
  static async deleteUserToken() {
    if(Platform.OS === "web")
        return await localStorage.removeItem(STORAGE_TOKEN_KEY);
    else
        return await SecureStore.deleteItemAsync(STORAGE_TOKEN_KEY);
  }
  static async getUserId() {
    if(Platform.OS === "web")
        return await localStorage.getItem(STORAGE_USER_ID_KEY);
    else
        return await SecureStore.getItemAsync(STORAGE_USER_ID_KEY);
  }
  static async saveUserId(id: string) {
    if(Platform.OS === "web")
        return await localStorage.setItem(STORAGE_USER_ID_KEY, id);
    else
        return await SecureStore.setItem(STORAGE_USER_ID_KEY, id);
  }
  static async deleteUserId() {
    if(Platform.OS === "web")
        return await localStorage.removeItem(STORAGE_USER_ID_KEY);
    else
        return await SecureStore.deleteItemAsync(STORAGE_USER_ID_KEY);
  }
  static async getProfileId() {
    if(Platform.OS === "web")
        return await localStorage.getItem(STORAGE_PROFILE_ID_KEY);
    else
        return await SecureStore.getItemAsync(STORAGE_PROFILE_ID_KEY);
  }
  static async saveProfileId(id: string) {
    if(Platform.OS === "web")
        return await localStorage.setItem(STORAGE_PROFILE_ID_KEY, id);
    else
        return await SecureStore.setItem(STORAGE_PROFILE_ID_KEY, id);
  }
  static async deleteProfileId() {
    if(Platform.OS === "web")
        return await localStorage.removeItem(STORAGE_PROFILE_ID_KEY);
    else
        return await SecureStore.deleteItemAsync(STORAGE_PROFILE_ID_KEY);
  }
}
