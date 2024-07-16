import AppRoutes from 'feature';
import { lazy } from 'react';
import {
    Route,
    BrowserRouter as Router,
    Routes as UserRoutes,
} from "react-router-dom";

const Loading = () => {
    
}

const Login = lazy(() => import('../feature/Login'))

export default function Routes() {
  return (
    <Router>
        <UserRoutes>
            <Route path='/' element={<Login />} />
        </UserRoutes>
        <AppRoutes />
    </Router>
  )
}
