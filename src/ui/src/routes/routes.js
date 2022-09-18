import { Login } from "../components/login/login";
import { Redirect, Route, useLocation, withRouter } from "react-router-dom";
import { Dashboard } from '../components/dashboard/dashboard';
import { Loader } from "../common/Loader";
import { Header } from "../components/header/header";
import { useIsFetching, useIsMutating } from 'react-query';


const DashboardWithHeader = () =>{ 
    const isFetching = useIsFetching();
    const isMutating = useIsMutating();

    const location = useLocation();

    return <>        
        <Header />
        <Dashboard self={location.state?.self} /> 
    </>
    };

const RoutesConfig = [
    {
        path: '/',
        component: Login
    },
    {
        path: '/dashboard',
        component: DashboardWithHeader
    }, 
    {
        path: '/assign-to-self',
        component: DashboardWithHeader,

    }
]


export const Routes = () => {
    return <>
        {
            RoutesConfig.map(({ path, component }, index) => <Route
                key={index}
                path={path}
                exact
                component={component}
            />)
        }
    </>
}