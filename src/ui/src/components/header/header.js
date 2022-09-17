import { FaRegUser } from 'react-icons/fa';
import { FlexBox, FlexItem } from "react-styled-flex";
import styled from "styled-components"


export const Header = () => {


    return <StyledHeader>
        <FlexItem flex={3} />
        <Title flex={4}>Space Allotment Tool</Title>
        <FlexItem flex={3} box justifyContent={'flex-end'} >
            <User >
                <FaRegUser />
                Vrushabh Girish Kapoor (M12345)
            </User>
        </FlexItem>
    </StyledHeader>
}

const User = styled(FlexBox)`
    align-items: center;
    gap:5px;
    font-size: 14px;
    justify-content: flex-end;
`;

const Title = styled(FlexItem)`
    font-size: 16px;
    text-align: center;
    width: 100%;
    font-weight: bold;
    justify-content: flex-end;
`;

const StyledHeader = styled(FlexItem)`
    height: 50px;
    background: black;
    padding: 0 36px;
    display: flex;
    align-items: center;
    color: white;
    display: flex;
`