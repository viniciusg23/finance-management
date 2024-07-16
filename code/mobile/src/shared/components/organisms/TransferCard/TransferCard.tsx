import React from "react";
import GroupCard from "shared/components/molecules/GroupCard";
import MainText from "shared/components/atoms/MainText";
import TransferContainer from "shared/components/molecules/TransferContainer/TransferContainer";

const TransferCard = () => {
	return (
		<GroupCard
			textTopLeft={
				<>
					<MainText
						variant="body2"
						style={{ fontFamily: "Montserrat_400Regular" }}
					>
						qui, 25 de abr
					</MainText>
					<MainText
						variant="caption"
						style={{
							fontFamily: "Montserrat_400Regular",
							alignSelf: "center",
						}}
					>
						(2)
					</MainText>
				</>
			}
			textTopRight={
				<MainText variant="body2" bold color="lightGreen">
					R$ 250,00
				</MainText>
			}
		>
			<TransferContainer />
			<TransferContainer />
		</GroupCard>
	);
};

export default TransferCard;

// border-bottom-width: 1px;
// border-bottom-color: #F1F2F3;
