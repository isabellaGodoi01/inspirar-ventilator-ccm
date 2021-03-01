import React, { useState, useEffect } from 'react'
import '../../style/formRegisterLogin.scss'
import { Link } from "react-router-dom"
import {ValidateEmail} from '../../../validation/emailValidation'
import logo from '../logo.png'
const ForgotPassword = () => {
  const [state, setState] = useState({
    email: '',
    formError: ''
  })

  useEffect(() => {
    setState({
      ...state,
      formError: '',
      formSuccess: false
    })
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [state.email])

  function handleForm (event) {
    event.preventDefault()
    if (!ValidateEmail(state.email)) {
      setState({
        ...state,
        formError: 'E-mail inválido.'
      })
    } else {
      fetch(`${process.env.REACT_APP_BASE_URL}usuario/recuperar-senha`,{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({email: state.email})
      }).then((res) => {
        if(res.status === 204) {
          setState({
            ...state,
            formSuccess: true
          })
        } else {
          setState({
            ...state,
            password: '',
            formError: 'E-mail inválido.'
          })
        }
      })
      
    }
  }

  return (
    <div className='container h-100 d-flex justify-content-center centerLogin'>
      {/* <div className='row h-100 alignBox'> */}
      <div className='alignBox'>
        <img src={logo} alt='Inspirar CCT' width={280} />
        <div className='description my-4'>
          <h4>Redefinição de senha</h4>
          <p className='mb-0'>Digite o seu e-mail no campo abaixo e <br /> lhe enviaremos uma nova senha.</p>
        </div>
        {state.formSuccess &&
          <p className='formSuccess'>
            Enviaremos para o seu e-mail as instruções de recuperação de senha.
          </p>
        }
        <form onSubmit={(event) => handleForm(event)} className='wrapFormLoginRegister'>
          <div className='wrapInput'>
            <input 
              type='email'
              placeholder='Digite seu e-mail'
              value={state.email}
              onChange={
                (event) => setState({
                  ...state,
                  email: event.target.value
                })}
            />
          </div>
          {state.formError &&
            <p className='formError'>{state.formError}</p>
          }
          <div className='wrapButton mb-3'>
            <input 
              disabled={!state.email || state.formError }
              type='submit'
              value='Enviar'
            />
          </div>
        </form>
        <span className='cadastrar'>Já tem conta? <Link to="/">Entrar</Link></span>
      </div>
    </div>
  )
}

export default ForgotPassword