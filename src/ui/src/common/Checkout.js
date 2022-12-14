import { FlexBox } from "react-styled-flex"
import styled from "styled-components"



export const Checkout = ({departmentName, oECode, employees, mpid, maxPercent, seatCounter}) => {

    return <CheckoutContainer width={'100%'} alignItems={'center'} gap="5%">
        <Title>{departmentName}</Title>
        <DetailsWrapper column >
            <SubTitle full >{oECode}</SubTitle>
            {!!employees && <SubText>{employees} Employees</SubText>}
            {!!mpid && <SubText>{mpid}</SubText>}
        </DetailsWrapper>
        {!!employees && <DetailsWrapper column>
            <SubTitle full >Max Allocable Space</SubTitle>
            <SubText>{Math.ceil(employees * maxPercent /100) - seatCounter} Units</SubText>
        </DetailsWrapper>}
        {!!seatCounter && <DetailsWrapper column>
            <SubTitle full >Allocated Seats</SubTitle>
            <SubText>{seatCounter} Units</SubText>
        </DetailsWrapper>}
    </CheckoutContainer>
}


const Title = styled.div`
    text-align: center;
    font-weight: bold;
    font-size: 20px;
    color: #000000;
`;

const SubTitle = styled.div`
    text-align: ${({center}) => center ? 'center' : 'left'};
    font-weight: bold;
    font-size:${({large}) => large ? '18px;' : '16px;'}; 
    color: #000000;
    width: ${({full}) => full ? '100%' : '40%'};
`;

const SubText = styled.div`
    font-size: 16px;
    font-weight: bold;
    color: rgb(124,124,123);
`;

const DetailsWrapper = styled(FlexBox)`
`;

const SelectionWrapper = styled(FlexBox)`
    justify-content: center;
    align-items: center;
    text-align: center !important;
    margin-top: 10%;
`;

const SelectionTable = styled(FlexBox)`
    margin-top: 4%;
    width: 100%;
`;

const Cell = styled(FlexBox)`
    border: 1px solid;
`

const CheckoutContainer = styled(FlexBox)`
    padding: 36px;
`;

