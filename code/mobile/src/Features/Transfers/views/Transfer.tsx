import React, { useState } from "react";
import { ScrollView, View } from "react-native";
import styles from "../styles";
import MainText from "shared/components/atoms/MainText";
import TransferCard from "shared/components/organisms/TransferCard/TransferCard";
import HorizontalPillFIlter from "shared/components/molecules/HorizontalPillFilter/HorizontalPillFIlter";
import MonthPill from "shared/components/molecules/MonthPill/MonthPill";

export const Transfer = () => {
	const Month = [
		"jan",
		"fev",
		"mar",
		"abr",
		"mai",
		"jun",
		"jul",
		"ago",
		"set",
		"out",
		"nov",
		"dez",
	];

	const [selectedFilter, setSelectedFilter] = useState("Geral");
	const [currentMonth, setCurrentMonth] = useState(new Date().getMonth());

	return (
		<View style={[styles.homePageContainer]}>
			<ScrollView>
				<View style={{ marginHorizontal: 15 }}>
					<View style={styles.header}>
						<View>
							<MainText variant="body2">
								seus lançamentos
							</MainText>
							<MainText variant="body1" bold>
								mensais
							</MainText>
						</View>
						<View
							style={{
								display: "flex",
								flexDirection: "row",
								alignItems: "baseline",
							}}
						>
							<MainText variant="body1">
								{Month[currentMonth]}{" "}
							</MainText>
							<MainText variant="body1" bold>
								2024
							</MainText>
						</View>
					</View>
					<MonthPill
						setMonth={setCurrentMonth}
						curMonth={currentMonth}
						month={Month}
					/>
					<HorizontalPillFIlter
						setSelectedFltr={setSelectedFilter}
						selectedFltr={selectedFilter}
					/>
					<TransferCard />
					<TransferCard />
				</View>
			</ScrollView>
		</View>
	);
};

export default Transfer;
