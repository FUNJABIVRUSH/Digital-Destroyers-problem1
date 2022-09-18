import React, { useContext, useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Switch } from 'react-router-dom';
import { Routes } from './routes/routes';
import { Header } from './components/header/header';
import styled from 'styled-components';
import GlobalStyles from './GlobalStyles';
import { QueryClient, QueryClientProvider } from 'react-query';

const Context = React.createContext();

export const useAppContext = () => useContext(Context);


const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      staleTime: 1000,
      retry: false,
      useErrorBoundary: true,
      refetchOnMount: 'always',

    },
    mutations: {
      retry: false,
      useErrorBoundary: true,
    }
  }
})

const App = () => {
  const [userData, setUserData] = useState(null);
  const [maxPercentage, setMaxPercentage] = useState(65);

  return <Context.Provider value={[userData, setUserData, maxPercentage, setMaxPercentage]}><QueryClientProvider client={queryClient}>
    <GlobalStyles />  
    <Router>
      <Switch>
        <StyledMain>
          <Routes />
        </StyledMain>
      </Switch>
    </Router>
  </ QueryClientProvider>
  </Context.Provider>
}


const StyledMain = styled.main`
  height: calc(100% - 50px);
`;

export default App;
