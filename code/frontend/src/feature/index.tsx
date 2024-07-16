import React from 'react'
import { Route, Routes } from 'react-router-dom'
import { InternalRoutes } from 'shared/utils/constants'
import Home from './Home/views/Home'
import Profiles from './Profiles/views/Profiles'
import Reports from './Reports/views/Reports'
import PrivateRoute from 'routes/PrivateRoute'

export default function AppRoutes() {
  return (
    <Routes>
        <Route path={InternalRoutes.HOME} element={<PrivateRoute><Home /></PrivateRoute>}/>
        <Route path={InternalRoutes.PROFILES} element={<PrivateRoute><Profiles /></PrivateRoute>}/>
        <Route path={InternalRoutes.REPORTS} element={<PrivateRoute><Reports /></PrivateRoute>}/>
    </Routes>
  )
}