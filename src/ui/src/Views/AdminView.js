import {FlexBox, FlexItem} from 'react-styled-flex';
import styled from 'styled-components';
import Select from 'react-select';
import { FaBlackberry } from 'react-icons/fa';
import {Tabs} from '../common/tabs';
import { Checkout } from '../common/Checkout';


const customStyles = {
    option: (provided, {isSelected}) => ({
      ...provided,
      padding: 20,
      background: isSelected  && '#000000',
      color: isSelected && '#FFFFFF',
      fontWeight: isSelected && 'bold',
      '&:hover': {
        background: '#DCDCDC',
        color: '#000000',
      },
    }),
  }
  

export const AdminView = () => {

    const departments = [
        { value: 'dept1', label: 'Department 1' },
        { value: 'dept2', label: 'Department 2' },
        { value: 'dept3', label: 'Department 3' }
    ];

    const oeCodes = [
        { value: 'OE1', label: 'HLOE 1' },
        { value: 'OE2', label: 'HLOE 2' },
        { value: 'OE3', label: 'HLOE 3' }
    ];
    

    return <AdminWrapper padding={'26px 36px 10px'} gap="5%" >
         <FormWrapper gap='10%' height={'100px'}>
                <FormContainer>
                    <span>Select Department</span>
                    <StyledSelect 
                        placeholder="Select Department..." 
                        styles={customStyles} 
                        options={departments} 
                    />
                </FormContainer>
                <FormContainer>
                    <span>Select OE</span>
                    <StyledSelect 
                        placeholder="Select Department..." 
                        styles={customStyles} 
                        options={oeCodes} 
                    />
                </FormContainer>
            </FormWrapper>

        <Shadow height={'100%'}>
                <Checkout />
                <Tabs />
            </Shadow>

    </AdminWrapper>
}

const FormWrapper = styled(FlexBox)`
    height: 100px;
    background: #DCDCDC;
    padding: 26px 36px 10px;
    justify-content: center;
    margin: -26px -36px -10px;
    
`;

const FormContainer = styled(FlexBox)`
    flex-direction: column;
    width: 18rem;
    gap: 10px;
    font-size:16px;
    span{
        font-weight: bold;
    }
`;

const AdminWrapper = styled(FlexBox)`
    width:100%;
    flex-direction: column;
    max-height: 100%;
`;

const Shadow = styled(FlexBox)`
    box-shadow: 0 0 16px rgb( 0 0 0 / 10%)
`;


const Tab = styled.button`
    color: rgb(124,124,123);
    background: none;
    width: 100px;
    height: 100%;
    font-weight: bold;
    align-items: center;
    justify-content: center;
    border: none;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 14px;
    cursor: pointer;
    ${
        ({active}) =>( active && `
            color: black;
            border-bottom: 3px solid black;
        `)
    }


`;

const StyledSelect = styled(Select)`
    .css-1s2u09g-control{
        min-width: ${({width}) => width || '18rem' };
        max-width: ${({width}) => width || '18rem' };
    }
    .css-1pahdxg-control{
        min-width: ${({width}) => width || '18rem' };
        max-width: ${({width}) => width || '18rem' };
        z-index:9;
    }
    .css-2613qy-menu{
        min-width: ${({width}) => width || '18rem' };
        max-width: ${({width}) => width || '18rem' };
    }
`

