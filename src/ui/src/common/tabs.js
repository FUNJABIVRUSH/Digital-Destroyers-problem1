import { useState } from "react";
import Select from "react-select";
import { FlexBox } from "react-styled-flex"
import styled from "styled-components";
import { SeatChart } from "../shared/seat-chart/seatChart";


const customStyles = {
    option: (provided, { isSelected }) => ({
        ...provided,
        padding: 20,
        height: '10px',
        alignItems: 'center',
        display: 'flex',
        background: isSelected && '#000000',
        color: isSelected && '#FFFFFF',
        fontWeight: isSelected && 'bold',
        '&:hover': {
            background: '#DCDCDC',
            color: '#000000',
        },
    }),
}



export const Tabs = ({ container: Container, onSelection, floorData, employee ,  maxPercent, seatCounter , allocatedSpace , isSeatAllocated }) => {

    const [selectedTab, setSelectedTab] = useState(floorData[0]?.floorId);

    const [zones, setZones] = useState(floorData[0]?.zones);

    const onTabChange = (floorId, zones) => {
        setSelectedTab(floorId);
        setZones(zones)
    }

    return <Main width={'100%'} flexDirection={'column'}>
        <FlexBox>
            <TabList>
                {
                    floorData.map((floor, index) => <Tab key={index}
                        active={selectedTab === floor.floorId}
                        onClick={() => onTabChange(floor.floorId, floor.zone)}>
                        {floor.floorName}
                    </Tab>)
                }
            </TabList>
            <TabContainer>
                <Container onSelection={onSelection} employee={employee} zones={zones} floor={selectedTab} floorData={floorData} />
            </TabContainer>
        </FlexBox>
        <LayoutChartDiv width={'95%'}>
            <SeatChart spaceInfo={floorData} selectedFloor={selectedTab} allocatedSpace={allocatedSpace} isSeatAllocated={isSeatAllocated}/>
        </LayoutChartDiv>
    </Main>
}


const TabList = styled(FlexBox)`
    flex-direction: column;
    width: 150px;
    height: 100%;
    border: none;
    border-right: 0;
    padding: 26px 36px 10px;
`;

const LayoutChartDiv = styled(FlexBox)`
    padding: 2rem;
`;

const Main = styled(FlexBox)`
    flex-direction: column;
`;

const Tab = styled.button`
    background: #FFFFFF;
    color: #FFFFFF;
    height: 40px;
    font-size: 16px;
    color: rgb(124,124,123);;
    cursor: pointer;
    border: none;
    border-left: 3px solid #DCDCDC;
    &:hover {
        font-weight: bold;
    }
    ${({ active }) => active && `
        font-weight: bold;
        color:  #000000;
        border-left: 3px solid #000000;
    `}
`;

const FieldsWrapper = styled(FlexBox)`
    justify-content: center;
    gap: 10px;
    align-items: center;
    height: 50px;
    margin-top: 3%;
`;

const TabContainer = styled(FlexBox)`
    border-left: 0;
    width: 80%;
    padding: 26px 36px 10px;    
`;

const StyledInput = styled.input`
    height: 1.8rem;
    width: 12rem;
`;


const Title = styled.div`
    text-align: center;
    font-weight: bold;
    font-size: 24px;
    color: #000000;
`;

const SubTitle = styled.div`
    text-align: left;
    font-weight: bold;
    font-size: 16px;
    color: #000000;
`;

const StyledSelect = styled(Select)`
    .css-1s2u09g-control{
        min-width: ${({ width }) => width || '12rem'};
        max-width: ${({ width }) => width || '12rem'};
    }
    .css-1pahdxg-control{
        min-width: ${({ width }) => width || '12rem'};
        max-width: ${({ width }) => width || '12rem'};
        z-index:9;
    }
    .css-2613qy-menu{
        min-width: ${({ width }) => width || '12rem'};
        max-width: ${({ width }) => width || '12rem'};
    }
`
