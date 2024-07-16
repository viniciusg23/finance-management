import { Alert } from "react-native";
import { ProfileService } from "services/ProfileService";
import { UserService } from "services/UserService";

export default function useProfile() {
	const getAllProfiles = async () => {
		const userService = new UserService();
		const profiles = await userService.getAllUserProfiles();
		return profiles;
	};

	const createProfile = async(profileName: string) => {
		const handleCreate = async() => await ProfileService.createProfile(profileName);

		Alert.alert("Perfil", `Tem certeza que deseja criar um perfil com o nome ${profileName}?`, [
			{
				text: "Não",
			},
			{
				text: "Sim",
				onPress: () => {
					handleCreate();
					Alert.alert("Perfil", "O perfil foi criado com sucesso.");
				},
			},
		])
	};

	const deleteProfile = async (profileId: string, callback: () => void) => {
		const handleDelete = async () =>
		  await ProfileService.deleteProfile(profileId);
	  
		Alert.alert("Perfil", "Tem certeza que deseja excluir este perfil?", [
		  {
			text: "Não",
		  },
		  {
			text: "Sim",
			onPress: async () => {
			  await handleDelete();
			  Alert.alert("Perfil", "O perfil foi excluido com sucesso.");
			  callback();
			},
		  },
		]);
	  };

	return {
		getAllProfiles,
		deleteProfile,
		createProfile
	};
}
