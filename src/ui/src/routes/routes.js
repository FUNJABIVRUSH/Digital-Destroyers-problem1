import { Login } from "../components/login/login";
import { Route } from "react-router-dom";


const RoutesConfig = [
    {
        path: '/login',
        component: Login
    }
]


export const Routes = () => {
    return RoutesConfig.map(({path, component}, index) => <Route 
    key={index}
    path={path}
    exact
    component={component}
/>)
}