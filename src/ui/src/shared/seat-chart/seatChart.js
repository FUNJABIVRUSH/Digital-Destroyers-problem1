import React, { useState } from "react";
import { FlexBox } from "react-styled-flex";
// import InputBase from '@mui/material/InputBase';
// import Select, { SelectChangeEvent } from '@mui/material/Select';
import styled from "styled-components";
import window from '../../asset/images/images1.png';
import { Paper, Button } from '@mui/material';
// import Box from '@mui/material/Box';
// import { LocalizationProvider } from '@mui/x-date-pickers-pro';
// import { AdapterDayjs } from '@mui/x-date-pickers-pro/AdapterDayjs';
// import { DateRangePicker } from '@mui/x-date-pickers-pro/DateRangePicker';
// import Switch from '@mui/material/Switch';
// import InputLabel from '@mui/material/InputLabel';
// import MenuItem from '@mui/material/MenuItem';
// import FormControl from '@mui/material/FormControl';
// import IconButton from '@mui/material/IconButton';
// import { FaSearchengin } from 'react-icons/fa';


const spaceInfo = {
    "buildingName": "EON",
    "floors": [
        {
            "floorId": 0,
            "floorName": "Level-1",
            "zones": [
                {
                    "seats": [
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "1",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "2",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "3",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "4",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "5",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "6",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "7",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "8",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "9",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "10",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "11",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "12",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "13",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "14",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "15",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "16",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "17",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "18",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "19",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "20",
                            "type": "string"
                        }
                    ],
                    "zoneId": 0,
                    "zoneName": "zone-A"
                },
                {
                    "seats": [
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "1",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "2",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "3",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "4",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "5",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "6",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "7",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "8",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "9",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "10",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "11",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "12",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "13",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "14",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "15",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "16",
                            "type": "string"
                        },
                        {
                            "isReserved": false,
                            "seatId": 0,
                            "seatName": "17",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "18",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "19",
                            "type": "string"
                        },
                        {
                            "isReserved": true,
                            "seatId": 0,
                            "seatName": "20",
                            "type": "string"
                        }
                    ],
                    "zoneId": 0,
                    "zoneName": "zone-B"
                }
            ]
        }
    ]
}

export const SeatChart = ({ spaceInfo, selectedFloor, allocatedSpace }) => {

    const [selectedSeat, selectSeat] = useState({ seatName: '1', zoneName: 'zone-A' });
    const [checkedSwitch, setCheckedSwitch] = useState(false);
    const [value, setValue] = useState([null, null]);

    let layoutSpaceArray = [...spaceInfo];
    let layoutSpace = [];

    allocatedSpace && allocatedSpace.length && allocatedSpace.map((space) => {
        layoutSpaceArray.map((layout, index) => {
            if (space.floorId === layout.floorId) {
                layout.zones.map((zone, zoneIndex) => {
                    if (zone.zoneId === space.zoneId) {
                        zone.seats.map((seat, seatIndex) => {
                            if ((seat.seatId >= space.startSeatId && seat.seatId <= space.endSeatId) || seat.seatId === space.startSeatId) {
                                layoutSpaceArray[index].zones[zoneIndex].seats[seatIndex].isBooked = true;
                                if (space.isAlreadyReserved) {
                                    layoutSpaceArray[index].zones[zoneIndex].seats[seatIndex].isAlreadyReserved = true;
                                } else {
                                    layoutSpaceArray[index].zones[zoneIndex].seats[seatIndex].isAlreadyReserved = false;
                                }
                            }
                            else {
                                layoutSpaceArray[index].zones[zoneIndex].seats[seatIndex].isBooked = false;
                                if (space.isAlreadyReserved) {
                                    layoutSpaceArray[index].zones[zoneIndex].seats[seatIndex].isAlreadyReserved = true;
                                } else {
                                    layoutSpaceArray[index].zones[zoneIndex].seats[seatIndex].isAlreadyReserved = false;
                                }
                            }
                        })
                    }
                })
            }
        });
    });

    return <StyledPaper elevation={3}>
        {
            layoutSpaceArray && layoutSpaceArray.length && layoutSpaceArray.map((floor) => {
                console.log('floor', layoutSpaceArray)
                if (selectedFloor == floor.floorName) {

                    return floor.zones.length && floor.zones.map((zone) => {
                        return <>
                            <StylesSubDiv>{`${floor.floorName}/${zone.zoneName}`}</StylesSubDiv>
                            <StyledTable>
                                <tbody>
                                    <tr>
                                        {zone && zone.seats.length && zone.seats.map((seat, i) => {
                                            console.log('seat', seat)
                                            if (i < 10)
                                                return <StyledTd><Button variant={`${(selectedSeat.seatName === seat.seatNumber && selectedSeat.zoneName === zone.zoneName) || seat.isBooked || seat.isAlreadyReserved ? 'contained' : 'outlined'}`} onClick={() => selectSeat({ seatName: seat.seatNumber, zoneName: zone.zoneName })} disabled={seat.isReserved || seat.isAlreadyReserved}>{seat.seatNumber}</Button></StyledTd>

                                        })}
                                    </tr>
                                    <tr>
                                        {zone && zone.seats.length && zone.seats.map((seat, i) => {
                                            if (i > 9)
                                                return <StyledTd><Button variant={`${(selectedSeat.seatName === seat.seatNumber && selectedSeat.zoneName === zone.zoneName) || seat.isBooked || seat.isAlreadyReserved ? 'contained' : 'outlined'}`} onClick={() => selectSeat({ seatName: seat.seatNumber, zoneName: zone.zoneName })} disabled={seat.isReserved || seat.isAlreadyReserved}>{seat.seatNumber}</Button></StyledTd>

                                        })}
                                    </tr>
                                </tbody>
                            </StyledTable>
                        </>
                    })
                }

            })
        }
        <StyledImage src={window} />
        <StyledSpan>Window here!</StyledSpan>
        <StyledLegendsDiv>
            <tr>
                <StyledTd><Button variant="contained" disabled>B</Button><StyledLegendText>Booked</StyledLegendText></StyledTd>
                <StyledTd><Button variant="outlined">A</Button><StyledLegendText>Available</StyledLegendText></StyledTd>
                <StyledTd><Button variant="contained">S</Button><StyledLegendText>Selected</StyledLegendText></StyledTd>
            </tr>
        </StyledLegendsDiv>
    </StyledPaper>
}

const StyledLoginContainer = styled(FlexBox)`
overflow:scroll;
flex: 1;
display: flex;
flex-direction:column;
padding: 15px 15px;
background: #fafafa;
width: 100%;
max-height: 80%;
`

const StyledSpan = styled.span`
text-align:center;
`

const StyledTable = styled.table`
margin-bottom:2rem;
`

const StyleAnchor = styled.a`
display: inline-block;
font-size: 10px;
line-height: 25px;
font-weight: 400;
background: #fff;
vertical-align: top;
width: 2rem;
height: 2rem;
text-align: center;
border: 1px solid;
`

const StyledImage = styled.img`
margin-top:2rem;
height:5rem;
align-self:center;
`

const StyledPaper = styled(Paper)`
flex: 1;
display: flex;
flex-direction: column;
background-color: #fafafa;
padding:1rem;
`;

const StyledInput = styled.input`
height: 30px;
`
const StyledLegendsDiv = styled.div`
align-self:center;
padding: 0.5rem;
background:white
`

const StylesSubDiv = styled.div`
width: 100%;
    color: #999;
    font-size: 12px;
    margin-bottom: 5px;
    text-align: left;
    padding-bottom: 10px;
    border-bottom: 1px solid #ececec;
`

const StyledDiv = styled.div`
flex:1;
display:flex;
flex-direction:row;
`
const StyledDivToggle = styled.div`
flex:1;
display:flex;
flex-direction:row;
align-self:flex-start;
`

const StyledDateRangeText = styled.p`
margin: 0px;
margin-right: 2rem;
`;

const StyledPText = styled.p`
margin: 10px;
`

const StyledShiftText = styled.p`
align-self:end;
margin-right: 1.8rem;
`

const StyledButton = styled.button`
height: 40px;
margin-top:10px;
background: black;
color: white;
cursor:pointer;
`;

const StyledTd = styled.td`
padding:10px;
flex:0.2
display:flex
`

const StyledDivShift = styled(StyledDiv)`
width:39%;
margin-top:1rem;
`

const StyledDivSearch = styled(StyledDiv)`
width:25%;
align-self:flex-end
`

const StyledLegendText = styled.p`
`