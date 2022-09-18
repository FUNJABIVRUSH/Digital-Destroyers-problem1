import styled from "styled-components"



export const Loader = <LoaderContainer ><span></span></LoaderContainer>



const LoaderContainer = styled.div`
    display: block;
	height: 32px;
	width: 32px;
	-webkit-animation: loader-2-1 3s linear infinite;
	animation: loader-2-1 3s linear infinite;
	z-index: 10000;
    position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	margin: auto;

    @-webkit-keyframes loader-2-1 {
        0% {
            -webkit-transform: rotate(0deg);
        }
        100% {
            -webkit-transform: rotate(360deg);
        }
    }
    @keyframes loader-2-1 {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }
    
    span {
        display: block;
        position: absolute;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        margin: auto;
        height: 32px;
        width: 32px;
        clip: rect(16px, 32px, 32px, 0);
        -webkit-animation: loader-2-2 1.5s cubic-bezier(0.770, 0.000, 0.175, 1.000) infinite;
        animation: loader-2-2 1.5s cubic-bezier(0.770, 0.000, 0.175, 1.000) infinite;
    }
`;
