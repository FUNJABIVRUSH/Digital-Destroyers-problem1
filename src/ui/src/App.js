import React from 'react';
import './App.css';
import { BrowserRouter as Router, Switch } from 'react-router-dom';
import { Routes } from './routes/routes';
import { Header } from './components/header/header';
import styled from 'styled-components';
import GlobalStyles from './GlobalStyles';
import { QueryClient, QueryClientProvider } from 'react-query';


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
  return <QueryClientProvider client={queryClient}>
  <GlobalStyles />
  <Header />
    <Router>
    <Switch>
      <StyledMain>
        <Routes />
      </StyledMain>
    </Switch>
  </Router>
</ QueryClientProvider>
}


const StyledMain = styled.main`
  height: calc(100% - 50px);
`;

export default App;
