import { FlexBox } from "react-styled-flex";
import { AdminView } from "../../Views/AdminView/AdminView";
import { LowLevelView } from "../../Views/RequesterView/RequesterView";


export const Dashboard = () => {


    return <>

        <FlexBox justifyContent="center" height="100%">
            <LowLevelView />
        </FlexBox>
    </>;
}

