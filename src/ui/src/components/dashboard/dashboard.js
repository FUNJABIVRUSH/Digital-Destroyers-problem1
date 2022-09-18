import { useContext } from "react";
import { FlexBox } from "react-styled-flex";
import { Context } from "../../App";
import { AdminContainer } from "../../Views/AdminView/AdminContainer";
import { AdminView } from "../../Views/AdminView/AdminView";
import { HighLevelContainer } from "../../Views/HighLevelView/HighlevelContainer";
import { HighLevelView } from "../../Views/HighLevelView/HighLevelView";
import { LowLevelView } from "../../Views/RequesterView/RequesterView";
import { LowLevelContainer } from "../../Views/RequesterView/RequesterViewContainer";


export const Dashboard = () => {

    const [userData, setUserData] = useContext(Context);
    return <>
        <FlexBox justifyContent="center" height="100%">
            {
                userData.role === 'ADMIN' ? <AdminView /> : userData.role === 'HIGH_LEVEL_MANAGER' ? <HighLevelView /> : userData.role === 'MID_LEVEL_MANAGER' ? <HighLevelView /> : <LowLevelView />
            }
        </FlexBox>
    </>;
}

