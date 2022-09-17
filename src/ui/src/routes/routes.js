import { Login } from "../components/login/login";
import { Redirect, Route } from "react-router-dom";
import {Dashboard} from '../components/dashboard/dashboard';


const RoutesConfig = [
    {
        path: '/login',
        component: Login
    },
    {
        path:'/dashboard',
        component: Dashboard
    }
]


export const Routes = () => {
    return <>
   { 
    RoutesConfig.map(({path, component}, index) => <Route 
        key={index}
        path={path}
        exact
        component={component}
    />)
    }
</>
}