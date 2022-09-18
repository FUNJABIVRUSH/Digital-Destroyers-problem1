import { createGlobalStyle } from "styled-components";



export default createGlobalStyle`

    html, body, #root {
        height: 100vh;
        margin: 0;
        padding: 0;
        max-height: 100vh;
        min-height: 100vh;
        overflow: hidden;

        /* Chrome, Safari, Edge, Opera */
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
        }

        /* Firefox */
        input[type=number] {
        -moz-appearance: textfield;
        }
    }
`