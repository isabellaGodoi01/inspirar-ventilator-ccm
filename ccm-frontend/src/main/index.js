import React from 'react'
import '../presentation/style/global.scss'
import {
  HashRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import { Login, Home, Register, ForgotPassword} from '../presentation/pages'

const Main = () => {
  return (
    <Router hashType='noslash'>
        <Switch>
          <Route path="/home">
            <Home />
          </Route>
          <Route path="/forgot-password">
            <ForgotPassword />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/">
            <Login />
          </Route>
        </Switch>
    </Router>
  )
}

export default Main