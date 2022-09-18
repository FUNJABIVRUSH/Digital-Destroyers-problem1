import { useState } from "react";
import Select from "react-select";
import { FlexBox } from "react-styled-flex"
import styled from "styled-components";


const customStyles = {
    option: (provided, {isSelected}) => ({
      ...provided,
      padding: 20,
      height: '10px',
      alignItems: 'center',
      display: 'flex',
      background: isSelected  && '#000000',
      color: isSelected && '#FFFFFF',
      fontWeight: isSelected && 'bold',
      '&:hover': {
        background: '#DCDCDC',
        color: '#000000',
      },
    }),
  }



export const Tabs = ({container: Container, onSelection, floorData, employees, maxPercent, seatCounter}) => {

    const [selectedTab, setSelectedTab] = useState(floorData[0]?.floorId);

    const [zones, setZones] = useState(floorData[0]?.zones);

    const onTabChange = (floorId, zones) => {
        setSelectedTab(floorId);
        setZones(zones)
    }

    return <FlexBox width={'100%'}>
        <TabList>
            {
                floorData.map((floor, index) => <Tab key={index} 
                    active={selectedTab === floor.floorId} 
                    onClick={() => onTabChange(floor.floorId, floor.zones)}>
                        {floor.floorName}
                </Tab>)
            }
        </TabList>
        <TabContainer>
            <Container onSelection={onSelection} employees={employees} zones={zones} floor={selectedTab} 
            maxPercent={maxPercent}
            seatCounter={seatCounter} />
        </TabContainer>
    </FlexBox>
}


const TabList = styled(FlexBox)`
    flex-direction: column;
    width: 150px;
    height: 100%;
    border: none;
    border-right: 0;
    padding: 26px 36px 10px;
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
    ${({active}) => active && `
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
        min-width: ${({width}) => width || '12rem' };
        max-width: ${({width}) => width || '12rem' };
    }
    .css-1pahdxg-control{
        min-width: ${({width}) => width || '12rem' };
        max-width: ${({width}) => width || '12rem' };
        z-index:9;
    }
    .css-2613qy-menu{
        min-width: ${({width}) => width || '12rem' };
        max-width: ${({width}) => width || '12rem' };
    }
`
