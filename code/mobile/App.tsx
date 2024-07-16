import { Text } from "react-native";
import {
	Montserrat_300Light,
	Montserrat_400Regular,
	Montserrat_500Medium,
	Montserrat_600SemiBold,
	useFonts,
} from "@expo-google-fonts/montserrat";

import { ThemeProvider } from "styled-components/native";
import theme from "./src/styles/theme";
import Routes from "./src/routes";
import LoadingScreen from "shared/components/molecules/Loading";

export default function App() {
	const [fontsLoaded] = useFonts({
		Montserrat_300Light,
		Montserrat_400Regular,
		Montserrat_500Medium,
		Montserrat_600SemiBold,
	});

	if (!fontsLoaded) {
		return <LoadingScreen />;
	}

	return (
		<ThemeProvider theme={theme}>
			<Routes />
		</ThemeProvider>
	);
}
