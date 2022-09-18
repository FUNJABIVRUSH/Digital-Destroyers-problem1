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


export const HighLevelContainer = () => {

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
    ];

    return <FlexBox width={'100%'} >
        <FieldsWrapper>
            <SubTitle>No of Seats to be alloted</SubTitle>
            <StyledInput /> / 200
        </FieldsWrapper>
        <FieldsWrapper>
            <SubTitle>Start seat Number</SubTitle>  
            <StyledSelect  options={options} styles={customStyles}></StyledSelect>      
        </FieldsWrapper>
    </FlexBox>
}

const FieldsWrapper = styled(FlexBox)`
    justify-content: center;
    gap: 10px;
    align-items: center;
    height: 50px;
    margin-left: 5%;
`;

const SubTitle = styled.div`
    text-align: left;
    font-weight: 500;
    font-size: 16px;
    color: #000000;
`;

const StyledInput = styled.input`
    height: 1.8rem;
    width: 4rem;
    padding: 0 10px;
    font-size: 14px;
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
