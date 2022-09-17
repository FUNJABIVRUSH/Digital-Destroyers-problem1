import Select from "react-select"
import { FlexBox } from "react-styled-flex"
import styled from "styled-components"



export const Checkout = () => {


    return <CheckoutContainer column>
        <Title>Department 1</Title>
            <DetailsWrapper column >
                <SubTitle large>OE 1</SubTitle>
                <SubText>OE Owner Name</SubText>
            </DetailsWrapper>
            <DetailsWrapper column>
                <SubTitle large >Employees</SubTitle>
                <SubText>500</SubText>
            </DetailsWrapper>
        <SelectionWrapper column>
            <SubTitle full center large>Seats Selected</SubTitle>
        </SelectionWrapper>
        <DetailsWrapper column>
            <SubTitle  >Floor 1</SubTitle>
            <SubText>Zone 1/1-  Zone 1/24</SubText>
        </DetailsWrapper>
        <DetailsWrapper column>
            <SubTitle  >Floor 2</SubTitle>
            <SubText>Zone 1/1-  Zone 1/24</SubText>
        </DetailsWrapper>

        <DetailsWrapper column>
            <SubTitle  large>Floor 2</SubTitle>
            <SubText>Zone 1/1-  Zone 1/24</SubText>
        </DetailsWrapper>

        <FlexBox>
            <button>Allot Space</button>
        </FlexBox>
    </CheckoutContainer>
}


const Title = styled.div`
    text-align: center;
    font-weight: bold;
    font-size: 24px;
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
    font-size: 13px;
    font-weight: bold;
    color: rgb(124,124,123);
`;

const DetailsWrapper = styled(FlexBox)`
    margin-top: 8%;
    margin-left: 8%;
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
    width: 50%;
    padding: 36px;
`;

