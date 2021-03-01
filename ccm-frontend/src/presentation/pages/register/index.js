import React, {useState, useEffect} from 'react'
import '../../style/formRegisterLogin.scss'
import {ValidateEmail} from '../../../validation/emailValidation'
import { Link } from "react-router-dom";
import logo from '../logo.png'

const Register = () => {
  const [state, setState] = useState({
    email: '',
    emailError: '',
    password: '',
    passwordError: '',
    confPassword: '',
    confPasswordError: '',
    nome: '',
    nomeError: '',
    perfil: 'ENFERMEIRO',
    formError: '',
    formSuccess: '',
  })

  useEffect(() => {
    if (ValidateEmail(state.email)) {
      setState({
        ...state,
        emailError: ''
      })
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [state.email])

  useEffect(() => {
    if (state.nome) {
      setState({
        ...state,
        nomeError: ''
      })
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [state.nome])

  useEffect(() => {
    if(state.password) {
      setState({
        ...state,
        passwordError: ''
      })
    }
    if (state.confPassword && state.password === state.confPassword) {
      setState({
        ...state,
        confPasswordError: ''
      })
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [state.password, state.confPassword])

  // function validationForm () {
  //   return new Promise((resolve) => {
  //     if (!ValidateEmail(state.email)) {
  //       setState({
  //         ...state,
  //         emailError: 'E-mail inválido.',
  //         formError: true
  //       })
  //     } else if (!state.nome) {
  //       setState({
  //         ...state,
  //         nomeError: 'Nome inválido.',
  //         formError: true
  //       })
  //     } else if (!state.password) {
  //       setState({
  //         ...state,
  //         passwordError: 'Senhas inválido.',
  //         formError: true
  //       })
  //     } else if (!state.confPassword) {
  //       setState({
  //         ...state,
  //         confPasswordError: 'Senhas inválido.',
  //         formError: true
  //       })
  //     } else if (state.password && state.password !== state.confPassword) {
  //       setState({
  //         ...state,
  //         confPasswordError: 'Senhas inválido.',
  //         formError: true
  //       })
  //     } else {
  //       setState({
  //         ...state,
  //         formError: false,
  //         formSuccess: true
  //       })
  //     }
  //     resolve(true)
  //   })
  // }

  function handleForm (event) {
    event.preventDefault()
    if (!ValidateEmail(state.email)) {
      setState({
        ...state,
        emailError: 'E-mail inválido.',
        formError: true
      })
    } else if (!state.nome) {
      setState({
        ...state,
        nomeError: 'Nome inválido.',
        formError: true
      })
    } else if (!state.password) {
      setState({
        ...state,
        passwordError: 'Senhas inválido.',
        formError: true
      })
    } else if (!state.confPassword) {
      setState({
        ...state,
        confPasswordError: 'Senhas inválido.',
        formError: true
      })
    } else if (state.password && state.password !== state.confPassword) {
      setState({
        ...state,
        confPasswordError: 'Senhas inválido.',
        formError: true
      })
    } else {
      fetch(`${process.env.REACT_APP_BASE_URL}usuario`,{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: state.email,
          password: state.password,
          perfil: state.perfil,
          name: state.nome,
          role: '',
          enabled: true
        })
      }).then((res) => {
        if(res.status === 204) {
          setState({
            ...state,
            email: '',
            password: '',
            confPassword: '',
            nome: '',
            perfil: 'ENFERMEIRO',
            formSuccess: true,
            formError: false
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
    <div className='container h-100'>
      <div className='row h-100 alignBox'>
        <img src={logo} alt='Inspirar CCT' width={280} />
        <div className='description'>
          <h4>Criar conta</h4>
          <p>Preencha os dados abaixo para criar sua conta</p>
        </div>
        {state.formSuccess &&
          <p className='formSuccess'>
            Usuário cadastrado com sucesso.
          </p>
        }
        <form onSubmit={(event) => handleForm(event)} className='wrapFormLoginRegister'>
          <div className='wrapInput'>
            <input 
              type='email'
              placeholder='Digite seu e-mail'
              value={state.email}
              maxLength={100}
              onChange={
                (event) => setState({
                  ...state,
                  email: event.target.value
                })}
            />
            {state.emailError && <span className='error'>Digite um e-mail válido</span>}
          </div>
          <div className='wrapInput'>
            <input 
              type='text'
              placeholder='Digite seu nome'
              maxLength={100}
              value={state.nome}
              onChange={
                (event) => setState({
                  ...state,
                  nome: event.target.value
                })}
            />
            {state.nomeError && <span className='error'>Digite um nome válido</span>}
          </div>
          <div className='wrapInput'>
            <select
              value={state.perfil}
              onChange={
                (event) => setState({
                  ...state,
                  perfil: event.target.value
                })}
            >
              <option value='ENFERMEIRO'>Enfermeiro</option>
              <option value='ADMINISTRADOR'>Administrador</option>
            </select>
          </div>
          <div className='wrapInput'>
            <input 
              type='password'
              placeholder='Digite uma senha'
              maxLength={25}
              value={state.password}
              onChange={
                (event) => setState({
                  ...state,
                  password: event.target.value
                })}
            />
            {state.passwordError && <span className='error'>Digite um senha válida</span>}
          </div>
          <div className='wrapInput'>
            <input 
              type='password'
              placeholder='Confirmar senha'
              maxLength={25}
              value={state.confPassword}
              onChange={
                (event) => setState({
                  ...state,
                  confPassword: event.target.value
                })}
            />
            {state.confPasswordError && <span className='error'>Digite uma confirmação de senha válida</span>}
          </div>
          <div className='wrapButton'>
            <input 
              disabled={
                !state.email || 
                !state.nome ||
                !state.password ||
                !state.confPassword
              }
              type='submit'
              value='Cadastrar'
            />
          </div>
        </form>
        <span className='cadastrar'>Já tem conta? <Link to="/">Entrar</Link></span>
      </div>
    </div>
  )
}

export default Register