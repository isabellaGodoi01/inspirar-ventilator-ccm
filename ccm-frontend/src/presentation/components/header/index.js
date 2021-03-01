import React, {useState} from 'react'
import { useHistory } from "react-router-dom"
import './header-style.scss'
import {ModalConfiguracoes} from '../../components'
import logo from './logo.png'
const Header = () => {
  const history = useHistory()
  const [showModalConfiguracoes, setShowModalConfiguracoes ] = useState(false)
  function logout () {
    localStorage.removeItem('bearerToken')
    localStorage.removeItem('username')
    history.push('/')
  }
  return (
    <>
    <div className='container'>
      <div className='row header'>
        <div className='col-6 header-logo'>
          <img src={logo} width={400} alt='logo' />
        </div>
        <div className='col-6 wrapLinksHeader'>
          <ul>
            <li className='namePerson'>{localStorage.getItem('username')} - {localStorage.getItem('role')} </li>
            {localStorage.getItem('role') === 'ADMINISTRADOR' &&
              <li className='config' onClick={() => setShowModalConfiguracoes(true) }>Configurações</li>
            }
            <li className='sair'><span onClick={logout}>Sair</span></li>
          </ul>
        </div>
      </div>
    </div>
    {showModalConfiguracoes && 
      <ModalConfiguracoes setShowModalConfiguracoes={setShowModalConfiguracoes} />
    }
    </>
  )
}

export default Header