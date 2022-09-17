import { useState } from "react";
import { FlexBox } from "react-styled-flex";
import styled from "styled-components";
import { getEmployeeByPID } from "../../shared/api";

export const Login = () => {

    const [empPID, setEmpPID] = useState('');

    const login = () => {
        getEmployeeByPID(empPID).then(response => console.log('res', response)
        );
    }

    return <StyledLoginContainer alignItems={'center'} justifyContent={'center'} column gap="15px">
        <StylesSubDiv>
            <h3>Welcome to SAT</h3>
            <h5>Please enter your MPID/PID to continue</h5>
            <StyledInput placeholder="Enter your MPID/PID" value={empPID} onChange={e => setEmpPID(e.target.value)} />
            <StyledButton onClick={login}>Login</StyledButton>
        </StylesSubDiv>
    </StyledLoginContainer>
}

const StyledLoginContainer = styled(FlexBox)`
align-items: center;
justify-content: center;
flex: 1;
display: flex;
padding: 15rem;
background-color: #e5e5e5;
`

const StyledInput = styled.input`
height: 30px;
`

const StylesSubDiv = styled.div`
border: 2px solid;
flex: 1;
align-items: center;
align-self: center;
justify-self: center;
display:flex;
flex-direction: column;
padding:10px;
background-color:white;
`

const StyledButton = styled.button`
height: 40px;
margin-top:10px;
background: black;
color: white;
cursor:pointer;
`;