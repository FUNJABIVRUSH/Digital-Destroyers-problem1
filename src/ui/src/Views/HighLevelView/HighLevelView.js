import {FlexBox} from 'react-styled-flex';
import styled from 'styled-components';
import Select from 'react-select';
import { Tabs } from '../../common/tabs';
import { HighLevelContainer } from './HighlevelContainer';


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



export const HighLevelView = () => {

    const oeCodes = [];

    const floors = [
        { value: 'floor1', label: 'Floor 1' },
        { value: 'floor2', label: 'Floor 2' },
        { value: 'floor3', label: 'Floor 3' }
    ];
    

    return <AdminWrapper padding={'26px 36px 10px'} gap="5%" >
         <FormWrapper gap='10%' height={'100px'}>
             <FlexBox gap='10%'>
                 <FormContainer column>
                    <span>Select OE code</span>
                    <StyledSelect 
                        placeholder="Select OE..." 
                        styles={customStyles} 
                        options={oeCodes} 
                    />
                </FormContainer>
                <FormContainer column>
                    <span>Select Floor</span>
                    <StyledSelect 
                        placeholder="Select Floor..." 
                        styles={customStyles} 
                        options={floors} 
                    />
                </FormContainer>
             </FlexBox>
             <FlexBox>
                <StyledButton>Allot Space</StyledButton>
             </FlexBox>
                
            </FormWrapper>

        <Shadow height={'100%'}>
                {/* <Checkout /> */}
                <Tabs container={ HighLevelContainer} />
        </Shadow>

    </AdminWrapper>
}

const FormWrapper = styled(FlexBox)`
    height: 100px;
    background: #DCDCDC;
    padding: 26px 36px 10px;
    justify-content: space-between;
    margin: -26px -36px -10px;
    align-items: center;
`;

const StyledButton = styled.button`
    background: #000000;
    color: #FFFFFF;
    border: 0;
    border-radius: 0;
    width: 150px;
    height: 40px;
`

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

