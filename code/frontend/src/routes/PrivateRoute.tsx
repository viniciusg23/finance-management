import { Navigate, useLocation } from 'react-router-dom';
import { Storage } from 'shared/utils/Storage';

interface PrivateRouteProps {
  children: React.ReactNode;
}

export default function PrivateRoute({ children }: PrivateRouteProps) {
  const userToken = Storage.getUserToken();
  const location = useLocation();

  return (userToken !== null && userToken !== undefined) ? <>{children}</> : <Navigate to="/" state={{ from: location }} />;
}