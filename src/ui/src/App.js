import React from 'react';
import './App.css';
import { BrowserRouter as Router, Switch } from 'react-router-dom';
import { Routes } from './routes/routes';
import { Header } from './components/header/header';
import styled from 'styled-components';
import GlobalStyles from './GlobalStyles';

const App = () => {
  return <>
  <GlobalStyles />
  <Header />
    <Router>
    <Switch>
      <StyledMain>
        <Routes />
      </StyledMain>
    </Switch>
  </Router>
</>
}


const StyledMain = styled.main`
  height: calc(100% - 50px);
`;

export default App;
