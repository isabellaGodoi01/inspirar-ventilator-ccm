import React, {useState} from 'react'
import './modalConfiguracoes-style.scss'


const ModalConfiguracoes = ({
  setShowModalConfiguracoes
}) => {
  const [state, setState] = useState({
    host: '',
    porta: '',
    autenticacao: true,
    usuario: '',
    senha: '',
    remetente: '',
    sslTls: true
  })


  function handleForm (event) {
    event.preventDefault()

    fetch(`${process.env.REACT_APP_BASE_URL}config/smtp`,{
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: localStorage.getItem('bearerToken')
      },
      body: JSON.stringify({
        enabled: true,
        host: state.host,
        porta: state.porta,
        autenticacao: state.autenticacao,
        usuario: state.usuario,
        senha: state.senha,
        remetente: state.remetente,
        sslTls: state.sslTls
      })
    }).then((res) => {
      setShowModalConfiguracoes(false)
    })
      
    
  }

  return (
    <div className='styleModal'>
      <div className='backdrop'></div>
      <div className='content contentModalConfiguracoes'>
        <div className='row headerModal'>
          <div className='col-4'>
          </div>
          <div className='col-4 center'>
          </div>
          <div className='col-4 closeStatus'>
            <span className='iconClose' onClick={() => setShowModalConfiguracoes(false) }>
              <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-x" fill="#b8b8b8" xmlns="http://www.w3.org/2000/svg">
                <path fillRule="evenodd" d="M11.854 4.146a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708-.708l7-7a.5.5 0 0 1 .708 0z"/>
                <path fillRule="evenodd" d="M4.146 4.146a.5.5 0 0 0 0 .708l7 7a.5.5 0 0 0 .708-.708l-7-7a.5.5 0 0 0-.708 0z"/>
              </svg>
            </span>
          </div>
        </div>

        <div className='box container'>
          <div className='row'>
            <div className='col-12 wrapTitleModal'>Configure os <b>parâmetros de SMTP</b> abaixo</div>
          </div>
          <form onSubmit={(event) => handleForm(event)}>
            <div className='row'>
              <div className='col-12'>
                <label>Host SMTP</label>
                <input 
                  type='text'
                  onChange={
                    (event) => setState({
                      ...state,
                      host: event.target.value
                    })}
                  required
                  placeholder='Digite o Host SMTP'
                  maxLength={100}
                />
              </div>
            </div>

            <div className='row'>
              <div className='col-6'>
                <label>Porta SMTP</label>
                <input 
                  type='number'
                  required
                  placeholder='Digite a porta SMTP'
                  onChange={
                    (event) => setState({
                      ...state,
                      porta: event.target.value
                    })}
                />
              </div>
              <div className='col-6'>
                <div className='nameWrapRadio'>
                  <span>Autenticação SMPTP</span>
                </div>
                <div className='wrapRadio'>
                  <input 
                    checked={state.autenticacao}
                    type='radio'
                    onChange={
                      (event) => setState({
                        ...state,
                        autenticacao: true
                      })}
                  />
                  <label>Habilitado</label>
                </div>
                <div className='wrapRadio'>
                  <input 
                    checked={!state.autenticacao}
                    type='radio'
                    onChange={
                      (event) => setState({
                        ...state,
                        autenticacao: false
                      })}
                  />
                  <label>Desabilitado</label>
                </div>
              </div>
            </div>

            <div className='row'>
              <div className='col-6'>
                <label>Usuário</label>
                <input
                  type='text'
                  required
                  maxLength={50}
                  placeholder='Digite o usuário'
                  onChange={
                    (event) => setState({
                      ...state,
                      usuario: event.target.value
                    })}
                />
              </div>
              <div className='col-6'>
                <label>Senha</label>
                <input 
                  type='password'
                  required
                  maxLength={25}
                  placeholder='Digite a senha'
                  onChange={
                    (event) => setState({
                      ...state,
                      senha: event.target.value
                    })}
                />
              </div>
            </div>

            <div className='row'>
              <div className='col-6'>
                <label>Remetente</label>
                <input 
                  type='email'
                  required
                  maxLength={100}
                  placeholder='Digite o remetente'
                  onChange={
                    (event) => setState({
                      ...state,
                      remetente: event.target.value
                    })}
                />
              </div>
              <div className='col-6'>
                <div className='nameWrapRadio'>
                  <span>SSL/TLS Habilitado</span>
                </div>
                <div className='wrapRadio'>
                  <input 
                    checked={state.sslTls}
                    type='radio'
                    onChange={
                      (event) => setState({
                        ...state,
                        sslTls: true
                      })}
                  />
                  <label>Sim</label>
                </div>
                <div className='wrapRadio'>
                  <input
                    checked={!state.sslTls}
                    type='radio'
                    onChange={
                      (event) => setState({
                        ...state,
                        sslTls: false
                      })}
                  />
                  <label>Não</label>
                </div>
              </div>
            </div>

            <div className='row sessionButtons'>
              <div className='col-6'>
                <button className='buttonCancel' onClick={() => setShowModalConfiguracoes(false) } >Cancelar</button>
              </div>
              <div className='col-6 wrapBtnSubmit'>
                <input type='submit' value='Salvar' />
              </div>
            </div>

          </form>
        </div>
      </div>
    </div>
  )
}

export default ModalConfiguracoes