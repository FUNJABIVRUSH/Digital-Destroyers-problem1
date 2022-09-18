import {FlexBox} from 'react-styled-flex';
import styled from 'styled-components';
import Select from 'react-select';
import { AdminContainer } from './AdminContainer';
import { Tabs } from '../../common/tabs';
import {useQuery} from 'react-query';
import { getDepartments, getLayout } from '../../shared/api';
import { useState, useTransition } from 'react';
import {Checkout} from '../../common/Checkout';


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
    const [departmentData, setDepartmentData] = useState([]);
    const [departments, setDepartments] = useState([]);
    const [layoutData , setLayoutData] = useState([]);
    const [selectedDept, setSelectedDept] = useState(null);
    const [empCount, setEmpCount] = useState(0);
    const [oeCodes, setOECodes] = useState([]);
    const [selectedOe, setSelectedOe] = useState(null);
    
    const [layoutSelection, setLayoutSelection] = useState({
        oeCodeId: '',
        preference: {},
        floorRequests: [],
    });

    useQuery('fetchDeptDetails', getDepartments, {
        onSuccess: (departmentData) => {
            const depts = [];
            if(departmentData && departmentData.length) {
                setDepartmentData(departmentData);
                departmentData.forEach(({departmentName, departmentId}) => {
                    depts.push({value:departmentId, label:departmentName });
                });
            }
            setDepartments(depts);
        }
    });

    useQuery('fetchLayoutDetails', getLayout, {
        onSuccess: (layoutData) => {
            if(layoutData && layoutData.floors &&  layoutData.floors.length) {
                setLayoutData(layoutData.floors);
            }
        }
    });

    const onChangeDepartment = (value) => {
        setSelectedDept(value);
        const oEcode = [];
        departmentData.forEach(({departmentId, oeCodeName,oeCodeId}) => {
            if(value.value === departmentId) {
                oEcode.push({value: oeCodeId,label: oeCodeName});
            }
        });
        setOECodes(oEcode);
    }

    const onChangeOE = (value) => {
        setSelectedOe(value);
        const {totalEmployees} = departmentData.find(({oeCodeId}) => oeCodeId === value.value);
        setEmpCount(totalEmployees);
        setLayoutSelection({...layoutSelection, oeCodeId: value.value })
    }

    const addFloorRequest = ({startSeat, endSeat, floor, zone, startSeatNum, endSeatNum}) => {
        const floorSet = {
            "endSeatId": endSeat,
            "startSeatId": startSeat,
        }
        const tmpLayourSelPref = {...layoutSelection.preference}; 
        layoutData.forEach(({floorId, floorName}) => {
            if(floorId === floor) {
                if(tmpLayourSelPref.hasOwnProperty(floorId)) {
                    tmpLayourSelPref[floorId].push({
                        floorName: floorName,
                        zone,
                        startSeatNum,
                        endSeatNum
                    })
                } else {
                    tmpLayourSelPref[floorId] = [{
                        floorName: floorName,
                        zone,
                        startSeatNum,
                        endSeatNum
                    }]
                }
            }
            
        });
        const floorRequests = [...layoutSelection.floorRequests, floorSet];
        setLayoutSelection({...layoutSelection, floorRequests, preference: tmpLayourSelPref});
    }

    const getFooterData = () => {
        return <SubText>
            {
                [...Object.keys(layoutSelection.preference)].map((key, i) => {
                    const objs = layoutSelection.preference[key];
                    return objs.map((obj, index, arr) =>  <span key={index}>{obj.floorName}: {obj.zone} {obj.startSeatNum} - {obj.zone} {obj.endSeatNum} {index + 1  < arr.length ? ' | ' : ''}</span> )
                    
                })
            }
        </SubText>
    }

    const onClearSelection = () => {
        setLayoutSelection({
            oeCodeId: '',
            preference: {},
            floorRequests: [],
        });
    }
    
    return <>
    <AdminWrapper padding={'26px 36px 10px'} gap="5%" >
         <FormWrapper gap='10%' height={'100px'}>
             <FlexBox gap='10%'>
                 <FormContainer column>
                    <span>Select Department</span>
                    <StyledSelect 
                        placeholder="Select Department..." 
                        styles={customStyles} 
                        options={departments} 
                        onChange={onChangeDepartment}
                        value={selectedDept}
                    />
                </FormContainer>
                {!!oeCodes.length && <FormContainer column>
                    <span>Select OE</span>
                    <StyledSelect 
                        placeholder="Select Department..." 
                        styles={customStyles} 
                        options={oeCodes}
                        onChange={onChangeOE}
                        value={selectedOe}   
                    />
                </FormContainer>}
             </FlexBox>    
            </FormWrapper>

       {!!selectedOe && <Shadow column height={'100%'}>
                <Checkout 
                    departmentName={selectedDept.label}
                    oECode={selectedOe.label}
                    employees={empCount}
                />
                <Tabs onSelection={addFloorRequest} floorData={layoutData} employees={empCount} container={AdminContainer} />
        </Shadow>}

    </AdminWrapper>
    {!!layoutSelection?.floorRequests?.length && <Footer>
        <FlexBox>
            <SubTitle>Selected Spaces:</SubTitle>
            {getFooterData()}
        </FlexBox>
        <FlexBox position='end' gap={'20px'}>
                <StyledButton variant='secondary' onClick={onClearSelection}>Clear Selection </StyledButton>
                <StyledButton>Allot Space</StyledButton>
        </FlexBox>
    </Footer>}
    </>
}


const SubTitle = styled(FlexBox)`
    text-align: ${({center}) => center ? 'center' : 'left'};
    font-weight: 500;
    font-size:${({large}) => large ? '18px;' : '16px;'}; 
    color: #000000;
     ${({center}) => center && 'align-items: center' };
     margin-left: 30px;
     margin-right: 5px;
`;

const SubText = styled.div`
    font-size: 16px;
    font-weight: 500;
    color: rgb(124,124,123);
    margin-left: 20px;
`;


const FormWrapper = styled(FlexBox)`
    height: 100px;
    background: #DCDCDC;
    padding: 26px 36px 10px;
    justify-content: space-between;
    margin: -26px -36px -10px;
    align-items: center;
`;

const StyledButton = styled.button`
    border: 0;
    background: ${({variant}) => (variant && variant === 'secondary' )? '#FFFFFF' : '#000000' };
    color: ${({variant}) =>  (variant && variant === 'secondary' ) ? '#000000' : '#FFFFFF' };
    ${({variant}) =>  (variant && variant === 'secondary' ) && 'border-color: #000000; border: 1px solid' };
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

const Footer = styled(FlexBox)`
    position: absolute;
    bottom: 0px;
    width: 100%;
    margin: -26px -36px -10px;
    border-top: 0.5px solid #DCDCDC;
    z-index: 1;
    background: #DCDCDC;
    padding: 26px 36px 26px 10px;
    justify-content: space-between;
    align-items: center;
`;

