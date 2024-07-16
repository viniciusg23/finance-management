import React from "react";
import { ScrollView, View } from "react-native";
import MainText from "shared/components/atoms/MainText";
import styles from "./styles";
import { BottomSheetScrollView } from "@gorhom/bottom-sheet";

const NotificationBotton = () => {
	return (
		<BottomSheetScrollView style={{ maxHeight: 260 }}>
			<View style={[styles.container]}>
				<View style={styles.titleContainer}>
					<MainText style={[styles.title]} bold>
						notificação
					</MainText>
				</View>
				<View>
					<MainText>
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto Texto Texto Texto Texto Texto Texto
						Texto Texto Texto
					</MainText>
				</View>
			</View>
		</BottomSheetScrollView>
	);
};

export default NotificationBotton;
