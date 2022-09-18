import styled from "styled-components";
import {ThreeDots} from "react-loader-spinner";

const LoaderContainer = styled.div`
    display: block;
	height: 50px;
	width: 50px;
	z-index: 10000;
    position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	margin: auto;

   
`;


export const Loader = () => <LoaderContainer ><ThreeDots 
    height="50" 
    width="50" 
    radius="20"
    color="#00000" 
    ariaLabel="three-dots-loading"
    wrapperStyle={{}}
    wrapperClassName=""
    visible={true}
 /> Loading...
 </LoaderContainer>



