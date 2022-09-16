import {FlexBox} from "react-styled-flex";
import styled from "styled-components";

export const Login = () => {

    return <StyledLoginContainer  alignItems={'center'} justifyContent={'center'} column  gap="15px">
        <h3>Welcome to SAT</h3>
        <h5>Please enter your MPID/PID to continue</h5>
        <StyledInput placeholder="Enter your MPID/PID" />
        <StyledButton>Login</StyledButton>
    </StyledLoginContainer>
}

const StyledLoginContainer = styled(FlexBox)`
    height: 100vh;
    max-width: 100%;
    min-width: 100%;
`

const StyledInput = styled.input`
height: 30px;
width: 20%;
}`

const StyledButton = styled.button`
height: 40px;
    width: 10%;
    background: black;
    color: white;
    cursor:pointer;
    `;