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



export const Tabs = () => {

    const [selectedTab, setSelectedTab] = useState('tab1');

    const options = [
        {
            label: 'Zone 1',
            options: [
                    {value: 'z1-1', label: '1'},
                    {value:'z1-2', label: '2'},
                    {value: 'z1-3', label: '3'}
            ]
        },
        {
            label: 'Zone 2',
            options: [
                    {value: 'z2-1', label: '1'},
                    {value: 'z2-2', label: '2'},
                    {value: 'z2-3', label: '3'}
            ]
        }
    ]

    return <FlexBox width={'100%'}>
        <TabList>
            <Tab active={selectedTab === 'tab1'} onClick={() => setSelectedTab('tab1')}>Floor 1</Tab>
            <Tab active={selectedTab === 'tab2'} onClick={() => setSelectedTab('tab2')}>Floor 2</Tab>
            <Tab active={selectedTab === 'tab3'} onClick={() => setSelectedTab('tab3')}>Floor 3</Tab>
            <Tab active={selectedTab === 'tab4'} onClick={() => setSelectedTab('tab4')}>Floor 4</Tab>
        </TabList>
        <TabContainer>
            <FlexBox width={'100%'} justifyContent='space-evenly'>
            <FieldsWrapper>
                <SubTitle>No of Seats to be alloted</SubTitle>
                <StyledInput />
            </FieldsWrapper>
            <FieldsWrapper>
                <SubTitle>Start seat Number</SubTitle>  
                <StyledSelect  options={options} styles={customStyles}></StyledSelect>      
            </FieldsWrapper>
            </FlexBox>
        </TabContainer>
    </FlexBox>
}


const TabList = styled(FlexBox)`
    flex-direction: column;
    width: 20%;
    height: 100%;
    border: none;
    border-right: 0;
    padding-top: 36px;
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
