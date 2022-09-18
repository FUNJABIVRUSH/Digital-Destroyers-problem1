
import { useHistory } from "react-router";
import styled from "styled-components";
import { RequesterView } from "../Views/RequesterView/RequesterView";


export const SelfAssign = () => {
    const history = useHistory();

    const onSelfClick = () => {
        history.push({
            pathname: '/assign-to-self',
            state: {
                self: true,
            }
        })
    } 

    return <LinkText onClick={onSelfClick}>Assign Seat to yourself</LinkText>  
} 

const LinkText = styled.label`
color: #000000;
text-decoration: underline;
font-size: 16px;
cursor:pointer;
font-weight:500;
`;
