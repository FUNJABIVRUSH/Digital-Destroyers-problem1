import { FlexBox } from "react-styled-flex";
import { AdminView } from "../../Views/AdminView/AdminView";
import { HighLevelView } from "../../Views/HighLevelView/HighLevelView";
import { LowLevelView } from "../../Views/LowLevelView.js/LowLevelView";
import { MidLevelView } from "../../Views/MidLevelView/MidLevelView";


export const Dashboard = () => {


    return <>

        <FlexBox justifyContent="center" height="100%">
            <LowLevelView />
        </FlexBox>
    </>;
}

