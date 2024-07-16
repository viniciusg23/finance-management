import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import InitialPage from "Features/InitialPage";
import Login from "Features/Login";
import AllProfilesPage from "Features/AllProfilesPage/AllProfilesPage";
import ConvitePage from "Features/ConvitePage/ConvitePage";
import NotificationPage from "Features/NotificationPage/NotificationPage";
import Register from "Features/Register";
import { useUserStore } from "store/UserStore";
import ApplicationScreen from "screens/app/ApplicationScreen";
import LoadingScreen from "shared/components/molecules/Loading";
import { useProfileStore } from "store/ProfileStore";


export type RootStackNavigatorScreens = {
	initial: undefined;
	login: undefined;
	register: undefined;
	application: { profileId: string } | undefined;
	allProfiles: undefined;
	convitePage: undefined;
	notificationPage: undefined;
};

type UserData = {
	userId?: string | null;
	name?: string | null;
	nickname?: string | null;
	token?: string | null;
	email?: string | null;
}

type ProfileData = {
	profileId?: string | null;
    user?: string | null;
    name?: string | null;
    createdAt?: Date | null;
}

const Stack = createStackNavigator<RootStackNavigatorScreens>();

const determineInitialRoute = (userData: UserData, profileData: ProfileData) => {
	if (userData?.token && profileData?.profileId) {
	  return "application";
	} else if (userData?.token && profileData.profileId == null) {
	  return "allProfiles";
	}
	return "initial";
  }

export default function Routes() {
	const { userData } = useUserStore();
	const { profileData } = useProfileStore();
	const initialRouteName = determineInitialRoute(userData, profileData);

	return (
		<NavigationContainer>
			<Stack.Navigator initialRouteName={initialRouteName} screenOptions={{ headerShown: false }}>
				<Stack.Screen name="initial" component={InitialPage} />
				<Stack.Screen name="login" component={Login} />
				<Stack.Screen name="register" component={Register} />
				<Stack.Screen name="application" component={ApplicationScreen} initialParams={{ profileId: profileData?.profileId ?? undefined}}/>
				<Stack.Screen name="allProfiles" component={AllProfilesPage} />
				<Stack.Screen name="convitePage" component={ConvitePage} />
				<Stack.Screen
					name="notificationPage"
					component={NotificationPage}
				/>
			</Stack.Navigator>

		</NavigationContainer>
	);
}
