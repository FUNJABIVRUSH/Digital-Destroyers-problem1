import { useEffect, useState } from "react";
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


export const LowLevelContainer = ({employees, zones, onSelection, floor}) => {

    const [maxSeating, setMaxSeating] = useState(0);
    const [seatCount, setSeatCount] = useState('');
    const [seatCountError, setSeatCountError] = useState(null);
    const [zoneOptions, setZoneOptions] = useState([]);
    const [selectedZone, setSelectedZone] = useState(null);
    const [availableSeats, setAvailableSeats] = useState([]);
    const [bookedSeats, setBookedSeats] = useState([]);
    const [seatOptions, setSeatOptions] = useState([]);

    const [startSeat, setStartSeat] = useState(0);
    const [endSeat, setEndSeat] = useState(0);
    const [startSeatNum, setStartSeatNum] = useState('');
    const [endSeatNum, setEndSeatNum] = useState('');
    const [selectedSeatValue, setSelectedSeatValue] = useState(null);



    useEffect(() => {
        setSelectedZone(null);
        if(zones) {
            const zone = []
            zones.forEach(({zoneId, zoneName, seats}) => {
                zone.push({value: zoneId, label: zoneName});
            });
    
            setZoneOptions(zone);
        }
    }, [zones])



    const onChangeSeatCount = (value) => {
        setSeatOptions([]);
        setSeatCountError(null);
        setSelectedSeatValue(null);
        setSeatCount(value);
        if(Number(value) && Number(value) <= 0) {
            setSeatCountError('Please enter a valid count');
        }
        if(Number(value) > maxSeating || Number(value) <= 0) {
            setSeatCountError('Please don\'t cross count');
        } else {
            setSeatCountError(null);
        }
        const {seatOption} = setSeatOptionsForSelection(selectedZone.value, value);
        console.log(seatOption);
        setSeatOptions(seatOption);
    }

    const setSeatOptionsForSelection = (value, seatCount) => {
        const {seats} = zones.find(({zoneId}) => zoneId === value);
        const booked= [];
        const available = [];
        const seatOption = [];
        seats.forEach(seat => {
            if(seat.isReserved) {
                booked.push(seat)
            } else {
                console.log(Number(seat.seatNumber), Number(seatCount), Number(maxSeating));
                if(((Number(seat.seatNumber) + Number(seatCount))- 1) <= Number(maxSeating)) {
                    seatOption.push({value: seat.seatId, label: seat.seatNumber});
                }
                available.push(seat);
            }
        });
        return {seatOption, available, booked};
    }


    const onZoneChange = (value) => {
        setAvailableSeats([]);
        setBookedSeats([]);
        setSelectedZone(value);
        setSeatCount('');
        setSeatCountError(null);
        setStartSeat(0);
        setEndSeat(0);
        setStartSeatNum('');
        setEndSeatNum('');
        setSeatOptions([]);
        setSelectedSeatValue(null);
        const {seatOption, available, booked} = setSeatOptionsForSelection(value.value, seatCount);
        setMaxSeating(available.length);
        setSeatOptions(seatOption);
        setAvailableSeats(available);
        setBookedSeats(booked);
    }


    const isSeatSelectionDisabled = () => {
        return seatCount.length <= 0 ||  seatCountError;
    }

    
    const onChangeStartSeat = (value) => {
        setSelectedSeatValue(value);
        const endSeatVal = (Number(value.label) + Number(seatCount)) - 1;
        const endSeatObj = availableSeats.find((seat) => seat.seatNumber === `${endSeatVal}`);
        setStartSeat(value.value);
        setEndSeat(endSeatObj.seatId);
        setStartSeatNum(value.label);
        setEndSeatNum(endSeatObj.seatNumber);
    }


    const isAddDisabled = () => {
        return isSeatSelectionDisabled() || startSeat <= 0 || endSeat <= 0
    }

    const onAddSelection = () => {
        onSelection({startSeat, endSeat, floor, zone: selectedZone.label, startSeatNum, endSeatNum});
        reset();
    }


    const reset = () => {
        setAvailableSeats([]);
        setBookedSeats([]);
        setSelectedZone(null);
        setSeatCount('');
        setSeatCountError(null);
        setStartSeat(0);
        setEndSeat(0);
        setStartSeatNum('');
        setEndSeatNum('');
        setSelectedSeatValue(null);
    }
    

    return <FlexBox width={'100%'} justifyContent={'space-between'} >
        <FieldsWrapper>
            <SubTitle>Select Zone </SubTitle>
            <StyledSelect value={selectedZone} onChange={onZoneChange} options={zoneOptions} styles={customStyles}></StyledSelect>   
        </FieldsWrapper>
    </FlexBox>
}

const StyledButton = styled.button`
    background: #000000;
    color: #FFFFFF;
    border: 0;
    border-radius: 0;
    width: 150px;
    height: 40px;
    ${({disabled}) => disabled && `background: #DCDCDC; pointer-events: none;`}
`

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
    .css-1insrsq-control {
        min-width: ${({width}) => width || '12rem' };
        max-width: ${({width}) => width || '12rem' };
    }
`
