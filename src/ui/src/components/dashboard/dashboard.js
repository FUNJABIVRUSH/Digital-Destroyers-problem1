import { useContext } from "react";
import { FlexBox } from "react-styled-flex";
import { Context, useAppContext } from "../../App";
import { AdminView } from "../../Views/AdminView/AdminView";
import { HighLevelView } from "../../Views/HighLevelView/HighLevelView";
import { LowLevelView } from "../../Views/LowLevelView.js/LowLevelView";
import { MidLevelView } from "../../Views/MidLevelView/MidLevelView";
import { RequesterView } from "../../Views/RequesterView/RequesterView";


export const Dashboard = () => {

    const [userData, setUserData] = useAppContext();

    console.log(userData);

    const getView = () => {
        switch (userData.role) {
            case 'ADMIN':
                return <AdminView />;
            case 'HIGH_LEVEL_MANAGER':
                return <HighLevelView />;
            case 'MID_LEVEL_MANAGER':
                return <MidLevelView /> 
            case 'LOW_LEVEL_MANAGER':
                return <LowLevelView /> 
            case 'EMPLOYEE':
                return <RequesterView /> 
    
            default:
                return <AdminView />;
                break;
        }
    }

    return <>
        <FlexBox justifyContent="center" height="100%">
            {getView()}
        </FlexBox>
    </>;
}

