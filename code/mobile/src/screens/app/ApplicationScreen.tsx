import {
	MaterialCommunityIcons,
	Octicons,
	AntDesign,
} from "@expo/vector-icons";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import Categories from "Features/Categories/views/Categories";
import HomePage from "Features/Home";
import UserScreen from "Features/ProfilePage";
import ManagerTab from "Features/TransactionTab";
import Transfer from "Features/Transfers";
import React from "react";
import { View } from "react-native";

export type RootBottomTabNavigatorScreens = {
	home: undefined;
	transactions: undefined;
	creation: undefined;
	categories: undefined;
	profile: undefined;
};

const Tab = createBottomTabNavigator<RootBottomTabNavigatorScreens>();

function ApplicationScreen() {
	return (
		<Tab.Navigator
			initialRouteName="home"
			screenOptions={{
				headerShown: false,
				tabBarStyle: {
					paddingVertical: 10,
					borderTopColor: "#FFF",
				},
				tabBarActiveTintColor: "#000",
				tabBarInactiveTintColor: "#BBB",
			}}
		>
			<Tab.Screen
				name="home"
				component={HomePage}
				options={{
					tabBarLabel: "Início",
					tabBarIcon: ({ color }) => (
						<Octicons name="home" size={24} color={color} />
					),
				}}
			/>
			<Tab.Screen
				name="transactions"
				component={Transfer}
				options={{
					tabBarLabel: "Transações",
					tabBarIcon: ({ color }) => (
						<AntDesign name="swap" size={24} color={color} />
					),
				}}
			/>
			<Tab.Screen
				name="creation"
				component={ManagerTab}
				options={{
					tabBarLabel: "Criar Transação",
					tabBarIcon: () => (
						<View
							style={{
								display: "flex",
								justifyContent: "center",
								alignItems: "center",
								backgroundColor: "#93eb33",
								borderRadius: 10,
								marginHorizontal: 10,
								width: 40,
								height: 40,
								marginBottom: 15,
							}}
						>
							<MaterialCommunityIcons
								name="plus-minus-variant"
								size={24}
								color="white"
							/>
						</View>
					)
				}}
			/>
			<Tab.Screen
				name="categories"
				component={Categories}
				options={{
					tabBarLabel: "Categorias",
					tabBarIcon: ({ color }) => (
						<MaterialCommunityIcons
							name="view-grid-plus-outline"
							size={24}
							color={color}
						/>
					),
				}}
			/>
			<Tab.Screen
				name="profile"
				component={UserScreen}
				options={{
					tabBarLabel: "Usuário",
					tabBarIcon: ({ color }) => (
						<Octicons name="person" size={24} color={color} />
					),
				}}
			/>
		</Tab.Navigator>
	);
}

export default ApplicationScreen;