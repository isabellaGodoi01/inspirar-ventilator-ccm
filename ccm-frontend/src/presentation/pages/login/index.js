import React, { useState, useEffect } from 'react'
import '../../style/formRegisterLogin.scss'
import { Link } from "react-router-dom";
import {ValidateEmail} from '../../../validation/emailValidation'
import { useHistory } from "react-router-dom"
import logo from '../logo.png'

const Login = () => {
  const history = useHistory()
  const [state, setState] = useState({
    email: '',
    password: '',
    formError: ''
  })

  useEffect(() => {
    if (ValidateEmail(state.email) && state.password.length > 3) {
      setState({
        ...state,
        formError: ''
      })
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [state.email, state.password])

  function handleForm (event) {
    event.preventDefault()
    if (state.email.length < 3 || state.password.length < 3) {
      setState({
        ...state,
        formError: 'Usuário ou senha inválido.'
      })
    } else {
      fetch(`${process.env.REACT_APP_BASE_URL}login`,{
        method: 'POST',
        body: JSON.stringify({
          username: state.email,
          password: state.password
        })
      }).then((res) => {
        if(res.status === 200) {
          res.json()
            .then((json) => {
              localStorage.setItem('bearerToken', json.token)
              localStorage.setItem('username', json.username)
              localStorage.setItem('role', json.role)
              history.push('/home')
            })
        } else {
          setState({
            ...state,
            password: '',
            formError: 'Usuário ou senha inválido.'
          })
        }
      })
    }
  }

  return (
    // <div className='container h-100'>
    //   <div className='row h-100 alignBox'>
    <div className='container h-100 d-flex justify-content-center centerLogin'>
      <div className='alignBox'>
        <img src={logo} alt='Inspirar CCT' width={280} className="mb-4"/>
        <form onSubmit={(event) => handleForm(event)} className='wrapFormLoginRegister'>
          <div className='wrapInput'>
            <input 
              type='text'
              placeholder='Digite seu e-mail'
              value={state.email}
              onChange={
                (event) => setState({
                  ...state,
                  email: event.target.value
                })}
            />
          </div>
          <div className='wrapInput'>
            <input 
              type='password'
              placeholder='Digite sua senha'
              value={state.password}
              onChange={
                (event) => setState({
                  ...state,
                  password: event.target.value
                })}
            />
          </div>
          {state.formError &&
            <p className='formError'>{state.formError}</p>
          }
          <div className='wrapButton mb-3'>
            <input 
              type='submit'
              value='Entrar'
              disabled={!state.email || !state.password }
            />
          </div>
        </form>
        <Link className='esqueciSenha' to="/forgot-password">Esqueci minha senha</Link>
        {/* <span className='cadastrar'>Não tem conta? <Link to="/register">Cadastre-se</Link></span> */}
      </div>
    </div>
  )
}

export default Login